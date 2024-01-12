package com.kh.zangzac.seongun.campboard.model.service;

import java.util.ArrayList;

import org.apache.ibatis.session.RowBounds;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.kh.zangzac.common.model.vo.Attachment;
import com.kh.zangzac.common.model.vo.PageInfo;
import com.kh.zangzac.common.model.vo.SelectCondition;
import com.kh.zangzac.common.photo.model.dao.PhotoDAO;
import com.kh.zangzac.common.photo.model.vo.Photo;
import com.kh.zangzac.common.reply.model.dao.ReplyDAO;
import com.kh.zangzac.common.reply.model.vo.Reply;
import com.kh.zangzac.seongun.campboard.model.dao.CampBoardDAO;
import com.kh.zangzac.seongun.campboard.model.vo.CampBoard;

@Service
public class CampBoardServiceImpl implements CampBoardService{
	@Autowired
	private CampBoardDAO cDAO;
	
	@Autowired
	private ReplyDAO rDAO;
	
	@Override
	public int getListCount(int i) {
		return cDAO.getListCount(i);
	}

	@Override
	public ArrayList<CampBoard> selectBoardList(PageInfo pi, int i) {
		RowBounds rowBounds = new RowBounds((pi.getCurrentPage()-1 )* pi.getBoardLimit(), 
		pi.getBoardLimit());
		return cDAO.selectBoardList(i, rowBounds);
	}

	@Override
	public int insertCampBoard(CampBoard board) {
		return cDAO.insertCampBoard(board);
	}

	@Override
	public int insertAttmCampBoard(ArrayList<Attachment> fileList) {
		return cDAO.insertAttmCampBoard(fileList);
	}

	@Override
	public CampBoard selectBoard(int cbNo, String id) {
		CampBoard b = cDAO.selectBoard(cbNo);
	      if(b != null) {
	         if(id != null && !b.getMemberId().equals(id)) {
	            int result = cDAO.updateCount(cbNo);
	            if(result > 0) {
	               b.setCbCount(b.getCbCount() + 1);
	            }
	         }
	      } 
		return b;
	}

	@Override
	public ArrayList<Reply> selectReply(SelectCondition b) {
		return rDAO.selectReply(b);
	}

}
