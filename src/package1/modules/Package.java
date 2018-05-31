package package1.modules;

public class Package {

    String funName;

    Object[] args;

    public Package(String funName,Object[] args){

        this.funName=funName;
        this.args=args;


    }

    public Object[] getArgs() {
        return args;
    }

    public String getFunName() {
        return funName;
    }

    @Override
    public String toString() {
        String stringArgs="";
        for(Object object:args)
           stringArgs+=object.toString()+"-";

        return stringArgs;
    }
}
