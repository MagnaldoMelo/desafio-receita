package br.com.mmelo.sincronizareceita.SpringApp;

/**
 * @author magnaldo_melo<magnaldo.melo@gmail.com>
 */
public abstract class AbstractLayout<T> {

    protected final String FIELD_DELIMITER = ";";
    public abstract T read(String line);
    public abstract Boolean validate(String line);
}
