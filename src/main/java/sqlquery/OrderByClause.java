package sqlquery;

import java.util.ArrayList;
import java.util.Arrays;

public class OrderByClause extends Clause{

    private String[] orders = {"asc", "desc"};
    private String[] aggregationKeys = {"avg", "sum", "count", "min", "max"};
    private boolean agrFlag = false;
    private String agr = null;

    private ArrayList<String> orderList;

    public OrderByClause(String keyword) {

        super(keyword);
        this.orderList = new ArrayList<>();
    }

    @Override
    public void addParam(String param) {
        if(Arrays.asList(this.orders).contains(param)){
            this.orderList.add(param);
        }else if (agrFlag){
            this.agrFlag = false;
            super.addParam(this.agr + "_" + param);
            this.agr = null;

        } else{
            if(Arrays.asList(this.aggregationKeys).contains(param)){
                this.agrFlag = true;
                this.agr = param;
            }else{
                super.addParam(param);
            }
        }
    }

    @Override
    public String toString() {
        String str = "Keyword: " + this.keyword + " | Params:";
        for(String param: this.parameters){
            str += " " + param;
        }
        str += " | Orders: ";
        for(String param: this.orderList){
            str += " " + param;
        }
        return str;
    }

    public ArrayList<String> getOrderList() {
        return orderList;
    }

    public void setOrderList(ArrayList<String> orderList) {
        this.orderList = orderList;
    }
}
