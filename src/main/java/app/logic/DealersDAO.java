package app.logic;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;

import app.beans.Dealer;

public class DealersDAO {

    public static List<Dealer> dealers;

    public void LoadDealers(JsonNode rootNode, ObjectMapper objectMapper) {
        dealers = new ArrayList<>();
        JsonNode dealersNode = rootNode.path("dealers");
        Iterator<JsonNode> d = dealersNode.elements();
        while (d.hasNext()) {
            try {
                dealers.add(objectMapper.treeToValue(d.next(), Dealer.class));
            } catch (JsonProcessingException e) {
                e.printStackTrace();
            }
        }

    }

    public List<Dealer> list() {
        return dealers;
    }

    public Dealer get(String id){
        for(Dealer dealer : dealers){
            if(dealer.getId().equals(id)){
                return dealer;
            }
        }
        return null;
    }
}