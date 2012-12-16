package com.parthtejani.listminer;


public class ListMinerFactory {

    private final String[] args;

    public ListMinerFactory(String[] args) {
        this.args = args;
    }

    public ListMiner build() {
        Parser parser = new Parser(Option.values());
        parser.parse(args);

        return new ListMiner(parser.getValues());
    }

}
