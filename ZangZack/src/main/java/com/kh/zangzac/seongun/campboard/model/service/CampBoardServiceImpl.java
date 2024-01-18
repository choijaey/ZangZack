package com.kh.zangzac.seongun.campboard.model.service;

import java.time.format.DateTimeFormatter;
import java.util.ArrayList;

import org.apache.ibatis.session.RowBounds;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.kh.zangzac.common.model.vo.PageInfo;
import com.kh.zangzac.common.model.vo.SelectCondition;
import com.kh.zangzac.common.photo.model.vo.Photo;
import com.kh.zangzac.common.reply.model.dao.ReplyDAO;
import com.kh.zangzac.common.reply.model.vo.Reply;
import com.kh.zangzac.seongun.campboard.model.dao.CampBoardDAO;
import com.kh.zangzac.seongun.campboard.model.vo.CampBoard;
import com.kh.zangzac.seongun.common.model.vo.SearchBoard;

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
		RowBounds rowBounds = new RowBounds((pi.getCurrentPage()-1 )* pi.getBoardLimit(), pi.getBoardLimit());
		ArrayList<CampBoard> list = cDAO.selectBoardList(i, rowBounds);
		for(CampBoard b : list) {
			format(b);
		}
		return list;
	}

	@Override
	public int insertCampBoard(CampBoard board) {
		return cDAO.insertCampBoard(board);
	}

	@Override
	public int insertAttmCampBoard(ArrayList<Photo> fileList) {
		return cDAO.insertAttmCampBoard(fileList);
	}

	@Override
	public CampBoard selectBoard(int cbNo, String id) {
		CampBoard b = cDAO.selectBoard(cbNo);
		format(b);
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

	@Override
	public int searchListCount(SearchBoard search) {
		return cDAO.searchListCount(search);
	}

	@Override
	public ArrayList<CampBoard> searchBoardList(PageInfo pi, SearchBoard search) {
		RowBounds rowBounds = new RowBounds((pi.getCurrentPage()-1 )* pi.getBoardLimit(), pi.getBoardLimit());
		ArrayList<CampBoard> list = cDAO.searchBoardList(search, rowBounds);
		for(CampBoard b : list) {
			format(b);
		}
		return list;
	}

	@Override
	public int deleteCampBoard(int cbNo) {
		return cDAO.deleteCampBoard(cbNo);
	}
	
	@Override
	public int updateCampBoard(CampBoard b) {
		return cDAO.updateCampBoard(b);
	}
	
	public void format(CampBoard b) {
		String DATE_TIME_FORMAT = "YYYY MM-dd";
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern(DATE_TIME_FORMAT);
        String create = b.getCbCreateDate().format(formatter);
        String modify = b.getCbModifyDate().format(formatter);
        
        if(create.equals(modify)) {
        	b.setFormatDate(create);
        }else {
        	b.setFormatDate("(수정)"+modify);
        }
    }
}
