package com.network.company.dao;

import com.network.domain.company.Company;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;

/**
 * @author zhangtao
 */
public interface CompanyDao extends JpaRepository<Company,String> ,JpaSpecificationExecutor<Company> {
}
