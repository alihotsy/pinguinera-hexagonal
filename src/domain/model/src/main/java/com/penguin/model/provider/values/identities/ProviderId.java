package com.penguin.model.provider.values.identities;

import com.penguin.model.generic.Identity;

public class ProviderId extends Identity {

    public ProviderId(){
        super();
    }

    public ProviderId(String id) {
        super(id);
    }

    public static ProviderId of(String uuid){
        return new ProviderId(uuid);
    }
}
