package com.budhash.cliche;

public interface CommandProcessor
{
    public void doCommand(ShellCommand command, Object[] parameters);
}