package net.tralfamadore.repository;

import net.tralfamadore.domain.ListingDetail;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;

/**
 * Created by Bill on 12/30/2016.
 */
@RepositoryRestResource(exported = false)
public interface ListingDetailRepository extends JpaRepository<ListingDetail,Long> {
    ListingDetail findByListingId(Long listingId);
}
