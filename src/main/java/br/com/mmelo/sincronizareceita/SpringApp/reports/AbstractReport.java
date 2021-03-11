package br.com.mmelo.sincronizareceita.SpringApp.reports;

public abstract class AbstractReport<T> {
    public abstract String line(T t);
}
