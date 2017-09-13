package org.thoughts.on.java.model;

import com.fasterxml.jackson.databind.ObjectMapper;
import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.Serializable;
import java.io.StringWriter;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Types;
import org.hibernate.HibernateException;
import org.hibernate.engine.spi.SharedSessionContractImplementor;
import org.hibernate.usertype.UserType;

public class ReviewTextType implements UserType {
    
    public static final ObjectMapper MAPPER = new ObjectMapper();

    @Override
    public int[] sqlTypes() {
        return new int[]{Types.OTHER};
    }

    @Override
    public Class returnedClass() {
        return ReviewText.class;
    }

    @Override
    public boolean equals(Object o, Object o1) throws HibernateException {
        if (o == null) {
            return o1 == null;
        }
        return o.equals(o1);
    }

    @Override
    public int hashCode(Object o) throws HibernateException {
        return o.hashCode();
    }

    @Override
    public Object nullSafeGet(ResultSet rs, String[] strings, SharedSessionContractImplementor ssci, Object o) throws HibernateException, SQLException {
        final String cellContent = rs.getString(strings[0]);      
        
        if (cellContent == null) {
            return null;
        }
        
        try {
            return MAPPER.readValue(cellContent.getBytes("UTF-8"), returnedClass());
        } catch (final Exception ex) {
            throw new RuntimeException("Failed to convert String to Invoice: " + ex.getMessage(), ex);
        }
    }

    @Override
    public void nullSafeSet(PreparedStatement ps, Object o, int i, SharedSessionContractImplementor ssci) throws HibernateException, SQLException {
        if (o == null) {
            ps.setNull(i, Types.OTHER);
            return;
        }
    	
        try {
            final StringWriter w = new StringWriter();
            MAPPER.writeValue(w, o);
            w.flush();
            ps.setObject(i, w.toString(), Types.OTHER);
        } catch (final Exception ex) {
            throw new RuntimeException("Failed to convert Invoice to String: " + ex.getMessage(), ex);
        }
    }

    @Override
    public boolean isMutable() {
        return true;
    }

    @Override
    public Serializable disassemble(Object o) throws HibernateException {
        return (Serializable) this.deepCopy(o);
    }

    @Override
    public Object assemble(Serializable srlzbl, Object o) throws HibernateException {
        return this.deepCopy(o);
    }

    @Override
    public Object replace(Object o, Object o1, Object o2) throws HibernateException {
        return this.deepCopy(o);
    }

    @Override
    public Object deepCopy(final Object value) throws HibernateException {
        try {
            // use serialization to create a deep copy
            ByteArrayOutputStream bos = new ByteArrayOutputStream();
            ObjectOutputStream oos = new ObjectOutputStream(bos);
            oos.writeObject(value);
            oos.flush();
            oos.close();
            bos.close();

            ByteArrayInputStream bais = new ByteArrayInputStream(bos.toByteArray());
            return new ObjectInputStream(bais).readObject();
        } catch (ClassNotFoundException | IOException ex) {
            throw new HibernateException(ex);
        }
    }

}
