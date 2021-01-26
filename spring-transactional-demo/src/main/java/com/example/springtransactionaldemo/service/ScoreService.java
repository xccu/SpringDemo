package com.example.springtransactionaldemo.service;

import com.example.springtransactionaldemo.mapper.ScoreMapper;
import com.example.springtransactionaldemo.model.Account;
import com.example.springtransactionaldemo.model.Score;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
public class ScoreService {

    @Autowired
    ScoreMapper scoreMapper;

    public List<Score> getAll(){
        return scoreMapper.selectList(null);
    }

    public void deleteAll(){
        scoreMapper.delete(null);
    }

    @Transactional(propagation = Propagation.REQUIRED )
    public void addRequired(){
        Score s = new Score(90,"java");
        scoreMapper.insert(s);
    }

    @Transactional(propagation = Propagation.REQUIRED )
    public void addRequiredException(){
        Score s = new Score(90,"java");
        scoreMapper.insert(s);

        throw new RuntimeException();
    }

    @Transactional(propagation = Propagation.REQUIRES_NEW )
    public void addRequiredNew(){
        Score s = new Score(90,"java");
        scoreMapper.insert(s);
    }

    @Transactional(propagation = Propagation.REQUIRES_NEW )
    public void addRequiredNew1(){
        Score s = new Score(95,"CSharp");
        scoreMapper.insert(s);
    }

    @Transactional(propagation = Propagation.REQUIRES_NEW )
    public void addRequiredNewException(){
        Score s = new Score(85,"python");
        scoreMapper.insert(s);
        throw new RuntimeException();
    }

    @Transactional(propagation = Propagation.NESTED )
    public void addNested(){
        Score s = new Score(90,"java");
        scoreMapper.insert(s);
    }

    @Transactional(propagation = Propagation.NESTED )
    public void addNestedException(){
        Score s = new Score(90,"java");
        scoreMapper.insert(s);
        throw new RuntimeException();
    }

    @Transactional(propagation = Propagation.SUPPORTS )
    public void addSupports(){
        Score s = new Score(90,"java");
        scoreMapper.insert(s);
    }

    @Transactional(propagation = Propagation.SUPPORTS )
    public void addSupportsException(){
        Score s = new Score(90,"java");
        scoreMapper.insert(s);
        throw new RuntimeException();
    }

    @Transactional(propagation = Propagation.NOT_SUPPORTED )
    public void addNotSupported(){
        Score s = new Score(90,"java");
        scoreMapper.insert(s);
    }

    @Transactional(propagation = Propagation.NOT_SUPPORTED )
    public void addNotSupportedException(){
        Score s = new Score(90,"java");
        scoreMapper.insert(s);
        throw new RuntimeException();
    }

    @Transactional(propagation = Propagation.MANDATORY )
    public void addMandatory(){
        Score s = new Score(90,"java");
        scoreMapper.insert(s);
    }

    @Transactional(propagation = Propagation.MANDATORY )
    public void addMandatoryException(){
        Score s = new Score(90,"java");
        scoreMapper.insert(s);
        throw new RuntimeException();
    }

    @Transactional(propagation = Propagation.NEVER )
    public void addNever(){
        Score s = new Score(90,"java");
        scoreMapper.insert(s);
    }

    @Transactional(propagation = Propagation.NEVER )
    public void addNeverException(){
        Score s = new Score(90,"java");
        scoreMapper.insert(s);
        throw new RuntimeException();
    }
}
