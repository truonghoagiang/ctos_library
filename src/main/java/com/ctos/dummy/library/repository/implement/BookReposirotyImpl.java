package com.ctos.dummy.library.repository.implement;

import com.ctos.dummy.library.dto.BookDetails;
import com.ctos.dummy.library.entity.Book;
import com.ctos.dummy.library.repository.custom.BookRepositoryCustom;
import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import jakarta.persistence.Query;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public class BookReposirotyImpl implements BookRepositoryCustom {

    private Logger logger = LoggerFactory.getLogger(BookReposirotyImpl.class);

    @PersistenceContext
    private EntityManager em;

    @Override
    public List<BookDetails> findByAisleNameAndLibraryName(String aisleName, String libraryName) {
        logger.info(Thread.currentThread().getStackTrace()[0].getMethodName());
        Query query = em.createNativeQuery(
                "select tb.id, tb.book_name , ta.aisle_name , tl.library_name \n" +
                        "from t_book tb, t_aisle ta, t_library tl\n" +
                        "where  tb.aisle_id = ta.aisle_id\n" +
                        "and ta.library_id = tl.id\n" +
                        "and ta.aisle_name = ?\n" +
                        "and tl.library_name = ?",
                BookDetails.class
        );
        query.setParameter(1, aisleName);
        query.setParameter(2, libraryName);
        return query.getResultList();
    }
}
