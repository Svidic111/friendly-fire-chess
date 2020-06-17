package com.ffirechess.dealer;

import com.ffirechess.ui.model.response.GameRest;
import com.ffirechess.ui.model.response.UserRest;

public interface GameDealer {
    boolean addToQueue(UserRest user, GameRest game);
}
