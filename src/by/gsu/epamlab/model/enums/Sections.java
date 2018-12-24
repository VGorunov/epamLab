package by.gsu.epamlab.model.enums;

import java.time.LocalDate;
import java.time.temporal.ChronoUnit;

public enum Sections {
    ALLTASKS {
        @Override
        public String getQueryTail() {
            return "SELECT * FROM tasks WHERE userId = ? " +
                    "AND fixed = 'false'" +
                    "AND recycleBin = 'false';";
        }
    },

    TODAY {
        @Override
        public String getQueryTail() {
            LocalDate today = LocalDate.now();
            return "SELECT * FROM tasks WHERE  userId = ? " +
                    "AND dateCompletion <='" + today + "'" +
                    "AND fixed = 'false' " +
                    "AND recycleBin = 'false';";
        }
    },
    TOMORROW {
        @Override
        public String getQueryTail() {
            LocalDate today = LocalDate.now();
            LocalDate tomorrow = today.plus(1, ChronoUnit.DAYS);
            return "SELECT * FROM tasks WHERE userId = ? " +
                    "AND dateCompletion = '" + tomorrow + "'" +
                    "AND fixed = 'false' " +
                    "AND recycleBin = 'false';";
        }
    },
    SOMEDAY {
        @Override
        public String getQueryTail() {
            LocalDate today = LocalDate.now();
            LocalDate tomorrow = today.plus(1, ChronoUnit.DAYS);
            return "SELECT * FROM tasks WHERE userId = ? " +
                    "AND dateCompletion > '" + tomorrow + "'" +
                    "AND fixed = 'false' " +
                    "AND recycleBin = 'false';";
        }
    },
    FIXED {
        @Override
        public String getQueryTail() {
            return "SELECT * FROM tasks WHERE userId = ? " +
                    "AND fixed = 'true';";
        }
    },
    RECYCLE_BIN {
        @Override
        public String getQueryTail() {
            return "SELECT * FROM tasks WHERE userId = ? " +
                    "AND recycleBin = 'true';";
        }
    };

    public abstract String getQueryTail();
}
