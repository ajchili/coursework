package com.kirinpatel.carlist.database;

public class Schema {

    public static final class CarTable {

        public static final String NAME = "cars";

        public static final class Cols {

            public static final String UUID = "uuid";
            public static final String MAKE = "make";
            public static final String MODEL = "model";
            public static final String TYPE = "type";
            public static final String YEAR = "year";
        }
    }
}
