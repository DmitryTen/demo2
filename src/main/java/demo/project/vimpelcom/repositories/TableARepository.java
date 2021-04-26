package demo.project.vimpelcom.repositories;

import demo.project.vimpelcom.generated.jooq.tables.pojos.TableA;
import demo.project.vimpelcom.generated.jooq.tables.records.TableARecord;
import org.jooq.DSLContext;
import org.jooq.Result;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Repository;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import static demo.project.vimpelcom.generated.jooq.Tables.TABLE_A;

@Repository
public class TableARepository {
    private static final Logger log = LoggerFactory.getLogger( TableARepository.class );


    private DSLContext context;

    public TableARepository(DSLContext context) {
        this.context = context;
    }


    public List<Integer> flushOldRecords(LocalDateTime threshold) {
        if (threshold == null) {
            return Collections.emptyList();
        }

        Result<TableARecord> result = context.selectFrom(TABLE_A)
                .where(TABLE_A.CDAT.lessThan(threshold))
                .fetch();

        List<Integer> list = new ArrayList<>();
        result.stream().forEach(record -> {
            int cnt = deleteById(record.getTableAId());
            if (cnt == 1)
                list.add(record.getTableAId());
        });

        return list;
    }

    public int deleteById(Integer tableAId) {
        return context.deleteFrom(TABLE_A)
                .where(TABLE_A.TABLE_A_ID.eq(tableAId))
                .execute();
    }

}
