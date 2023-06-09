package com.arkinefed.luminous.data.order;

import java.util.List;

public class OrderRequest {
    private List<Long> ids;

    public OrderRequest(List<Long> ids) {
        this.ids = ids;
    }

    public List<Long> getIds() {
        return ids;
    }

    public void setIds(List<Long> ids) {
        this.ids = ids;
    }
}
