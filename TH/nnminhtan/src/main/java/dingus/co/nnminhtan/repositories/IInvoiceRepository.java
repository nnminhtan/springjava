package dingus.co.nnminhtan.repositories;

import dingus.co.nnminhtan.entities.Invoice;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
@Repository
public interface IInvoiceRepository extends JpaRepository<Invoice, Long>{
}
