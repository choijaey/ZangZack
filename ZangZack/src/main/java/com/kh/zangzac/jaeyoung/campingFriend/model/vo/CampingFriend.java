package com.kh.zangzac.jaeyoung.campingFriend.model.vo;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@ToString
public class CampingFriend {
	private int cfNo;
	private String cfContent;
	private String cfTitle;
	private String memberId;
	private String categoryName;
	private int categoryNo;
	private int replyCount;
	private int likeCount;
	private String photoPath;

}
