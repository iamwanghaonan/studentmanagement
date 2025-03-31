package com.wzu.dao;
import com.wzu.pojo.Massage;
import com.wzu.pojo.MassageReply;

import java.util.List;

public interface MassageDao {
    List<Massage> getMassage();
    boolean addMassage(Massage massage);
    boolean replyMassage(MassageReply massageReply, String messageid);
    List<MassageReply> getReply(String id);
    Massage getMessageInfo(String messageid);
}
