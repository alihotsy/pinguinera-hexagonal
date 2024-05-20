package com.penguin.model.provider.values.identities;

import com.penguin.model.generic.Identity;

public class CopyId extends Identity {

        public CopyId(){
            super();
        }

        public CopyId(String id) {
            super(id);
        }

        public static CopyId of(String uuid){
            return new CopyId(uuid);
        }
}
