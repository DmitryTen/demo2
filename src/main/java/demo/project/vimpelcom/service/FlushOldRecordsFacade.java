package demo.project.vimpelcom.service;

import demo.project.vimpelcom.controllers.models.DeleteResponse;
import demo.project.vimpelcom.repositories.TableARepository;
import demo.project.vimpelcom.repositories.TableBRepository;
import demo.project.vimpelcom.repositories.TableCRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.time.LocalDateTime;
import java.util.List;
import java.util.concurrent.Executor;

@Service
public class FlushOldRecordsFacade {

    private TableARepository tableARepository;
    private TableBRepository tableBRepository;
    private TableCRepository tableCRepository;

    @Autowired
    public FlushOldRecordsFacade(TableARepository tableARepository, TableBRepository tableBRepository, TableCRepository tableCRepository) {
        this.tableARepository = tableARepository;
        this.tableBRepository = tableBRepository;
        this.tableCRepository = tableCRepository;
    }

    public List<Integer> deleteFromTableA(LocalDateTime dateTime) {
        return tableARepository.flushOldRecords(dateTime);
    }

    public List<Long> deleteFromTableB(LocalDateTime dateTime) {
        return tableBRepository.flushOldRecords(dateTime);
    }

    public List<Long> deleteFromTableC(LocalDateTime dateTime) {
        return tableCRepository.flushOldRecords(dateTime);
    }



    public Integer createTableARecord(String name) {
        return tableARepository.createRecord(name);
    }

    public Long createTableBRecord(String name) {
        return tableBRepository.createRecord(name);
    }

    public Long createTableCRecord(String name) {
        return tableCRepository.createRecord(name);
    }
}
