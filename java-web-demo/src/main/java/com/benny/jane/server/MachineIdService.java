package com.benny.jane.server;

import com.benny.jane.exception.MessageIdException;
import com.benny.jane.utils.MessageIdService;

public class MachineIdService implements MessageIdService {
    @Override
    public Long getMachineId() throws MessageIdException {
        return 10L;
    }
}
