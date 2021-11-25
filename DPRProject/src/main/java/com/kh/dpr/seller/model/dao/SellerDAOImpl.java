package com.kh.dpr.seller.model.dao;

import org.mybatis.spring.SqlSessionTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.kh.dpr.seller.model.vo.Seller;

@Repository
public class SellerDAOImpl implements SellerDAO {

	@Autowired
	SqlSessionTemplate sqlSession;
	
	@Override
	public int insertSeller(Seller seller) {
		return sqlSession.insert("sellerSQL.insertSeller", seller);
	}

	@Override
	public int checkSellerDuplicate(String sellerId) {
		return sqlSession.selectOne("sellerSQL.checkSellerDuplicate",sellerId);
	}

	@Override
	public Seller selectOneSeller(String sellerId) {
		return sqlSession.selectOne("sellerSQL.selectOneSeller", sellerId);	
	}
	
	@Override
	public int updateSeller(Seller seller) {
		return sqlSession.update("sellerSQL.updateSeller", seller);
	}

	@Override
	public int deleteSeller(String sellerId) {
		return sqlSession.delete("sellerSQL.deleteSeller", sellerId);
	}
}
