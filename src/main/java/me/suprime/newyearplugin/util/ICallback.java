package me.suprime.newyearplugin.util;

@FunctionalInterface
public interface ICallback<Reply> {

    void done(Reply t);
}
