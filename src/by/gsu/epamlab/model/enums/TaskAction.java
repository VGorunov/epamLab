package by.gsu.epamlab.model.enums;

public enum TaskAction {
    FIX {
        @Override
        public String getQueryTail() {
            return "UPDATE tasks SET fixed = 'true' WHERE taskId = ?;";
        }
    },
    DELETE {
        @Override
        public String getQueryTail() {
            return "UPDATE tasks SET recycleBin = 'true' WHERE taskId = ?;";
        }
    },
    RESTORE {
        @Override
        public String getQueryTail() {
            return "UPDATE tasks SET recycleBin = 'false' WHERE taskId = ?;";
        }
    },
    DESTROY {
        @Override
        public String getQueryTail() {
            return "DELETE FROM tasks WHERE taskId = ?;";
        }
    };
    public abstract String getQueryTail();
}
