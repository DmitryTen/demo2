package demo.project.vimpelcom.repositories;

import demo.project.vimpelcom.generated.jooq.tables.records.TableBRecord;
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

@Repository
public class TableBRepository {

    private static final Logger log = LoggerFactory.getLogger( TableBRepository.class );


    private DSLContext context;

    public TableBRepository(DSLContext context) {
        this.context = context;
    }


    public List<Long> flushOldRecords(LocalDateTime threshold) {
        if (threshold == null) {
            return Collections.emptyList();
        }

        Result<TableBRecord> result = context.selectFrom(TABLE_B)
                .where(TABLE_B.CDAT_B.lessThan(threshold))
                .fetch();

        List<Long> list = new ArrayList<>();
        result.parallelStream().forEach(record -> {
            int cnt = deleteById(record.getTableBId());
            if (cnt == 1)
                list.add(record.getTableBId());
        });
        log.info("successfully deleted {} records", list.size());


        return list;
    }

    public int deleteById(Long tableAId) {
        return context.deleteFrom(TABLE_B)
                .where(TABLE_B.TABLE_B_ID.eq(tableAId))
                .execute();
    }


    public Long createRecord(String name) {
        Record1<Long> record = context.insertInto(TABLE_B)
                .columns(TABLE_B.NAME_B, TABLE_B.CDAT_B)
                .values(name, LocalDateTime.now())
                .returningResult(TABLE_B.TABLE_B_ID).fetchOne();

        return record.value1();
    }
}
