package database.sql.mistery;

public abstract class Record<R> {

    private R record;

    public void save(){
        System.out.println("test"+ record.toString());
    }
}
