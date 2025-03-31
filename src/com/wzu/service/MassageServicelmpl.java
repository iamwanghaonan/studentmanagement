package com.wzu.service;


import com.wzu.dao.MassageDao;
import com.wzu.dao.MassageDaolmpl;
import com.wzu.pojo.Massage;
import com.wzu.pojo.MassageReply;

import java.util.List;

public class MassageServicelmpl implements MassageService {
    MassageDao MassageDao = new MassageDaolmpl();
    @Override
    public List<Massage> getMassage() { return MassageDao.getMassage(); }
    @Override
    public boolean addMassage(Massage massage) {
        return MassageDao.addMassage(massage);
    }

    @Override
    public boolean replyMassage(MassageReply massageReply, String messageid) {
        return MassageDao.replyMassage(massageReply, messageid);
    }

    @Override
    public List<MassageReply> getReply(String id) {
        return MassageDao.getReply(id);
    }

    @Override
    public Massage getMessageInfo(String messageid) {
        return MassageDao.getMessageInfo(messageid);
    }
}
