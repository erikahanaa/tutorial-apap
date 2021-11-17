package apap.tutorial.pergipergi.service;

import apap.tutorial.pergipergi.model.RoleModel;
import apap.tutorial.pergipergi.repository.RoleDb;

import java.util.List;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
@Transactional
public class RoleServiceImpl implements RoleService{
    @Autowired
    private RoleDb roleDb;

    @Override
    public List<RoleModel> getListRole(){
        return roleDb.findAll();
    }
}
