package com.benny.jane.utils;

import com.benny.jane.exception.MessageIdException;

public interface MessageIdService {
    /**
     * 生成MachineId的方法
     *
     * @return machineId 机器码
     * @throws  MessageIdException 获取机器码可能因为外部因素失败
     */
    Long getMachineId() throws MessageIdException, MessageIdException;
}
