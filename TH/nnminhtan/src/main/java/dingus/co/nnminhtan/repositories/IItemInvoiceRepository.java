package dingus.co.nnminhtan.repositories;

import dingus.co.nnminhtan.entities.ItemInvoice;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface IItemInvoiceRepository extends JpaRepository<ItemInvoice, Long>{
}
