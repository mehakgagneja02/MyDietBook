package edmt.dev.mydietbook;
public class Db {
    String dbId;
    String dbdate;
    String dbtext;

    public Db(){

    }

    public Db(String dbId, String dbdate,String dbtext){
        this.dbId=dbId;
        this.dbdate=dbdate;
        this.dbtext=dbtext;


    }
    public String getdbId(){
        return dbId;
    }
    public String getdbdate(){
        return dbdate;
    }
    public String getdbtext() {
        return dbtext;
    }

}


