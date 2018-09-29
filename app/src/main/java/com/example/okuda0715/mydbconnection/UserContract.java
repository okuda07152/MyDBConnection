package com.example.okuda0715.mydbconnection;

import android.provider.BaseColumns;

public final class UserContract {

    public UserContract(){}

    public abstract static class User implements BaseColumns{
        public static final String TABLE_NAME = "user";
        public static final String COL_NAME = "name";
        public static final String COL_SCORE = "score";
    }
}
