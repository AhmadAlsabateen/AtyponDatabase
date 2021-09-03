package dto;

public class Query {
    public String type;
    public String Table;
    public Filter filter;
    public Input input;

    public Query() {
    }

    public Query(String type, String table, Filter filter, Input input) {
        this.type = type;
        Table = table;
        this.filter = filter;
        this.input = input;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getTable() {
        return Table;
    }

    public void setTable(String table) {
        Table = table;
    }

    public Filter getFilter() {
        return filter;
    }

    public void setFilter(Filter filter) {
        this.filter = filter;
    }

    public Input getInput() {
        return input;
    }

    public void setInput(Input input) {
        this.input = input;
    }
}
