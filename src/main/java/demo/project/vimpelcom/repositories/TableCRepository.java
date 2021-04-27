package demo.project.vimpelcom.repositories;

import demo.project.vimpelcom.generated.jooq.tables.records.TableBRecord;
import demo.project.vimpelcom.generated.jooq.tables.records.TableCRecord;
import org.jooq.DSLContext;
import org.jooq.Record1;
import org.jooq.Result;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Repository;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import static demo.project.vimpelcom.generated.jooq.Tables.TABLE_B;
import static demo.project.vimpelcom.generated.jooq.Tables.TABLE_C;

@Repository
public class TableCRepository {

    private static final Logger log = LoggerFactory.getLogger( TableCRepository.class );

    private DSLContext context;

    public TableCRepository(DSLContext context) {
        this.context = context;
    }


    public List<Long> flushOldRecords(LocalDateTime threshold) {
        if (threshold == null) {
            return Collections.emptyList();
        }

        Result<TableCRecord> result = context.selectFrom(TABLE_C)
                .where(TABLE_C.CDAT_C.lessThan(threshold))
                .fetch();

        List<Long> list = new ArrayList<>();
        result.parallelStream().forEach(record -> {
            int cnt = deleteById(record.getTableCId());
            if (cnt == 1)
                list.add(record.getTableCId());
        });

        log.info("successfully deleted {} records", list.size());

        return list;
    }

    public int deleteById(Long tableAId) {
        return context.deleteFrom(TABLE_C)
                .where(TABLE_C.TABLE_C_ID.eq(tableAId))
                .execute();
    }

    public Long createRecord(String name) {
        Record1<Long> record = context.insertInto(TABLE_C)
                .columns(TABLE_C.NAME_C, TABLE_C.CDAT_C)
                .values(name, LocalDateTime.now())
                .returningResult(TABLE_C.TABLE_C_ID).fetchOne();
        log.info("created record {}, name {}", record.value1(), name);

        return record.value1();
    }

}
