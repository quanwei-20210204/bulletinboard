package com.sap.cc.bulletinboard.ads;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
//@EnableJpaRepositories
//@EnableTransactionManagement
public interface AdvertisementRepository extends JpaRepository<Advertisement, Long> {

}
