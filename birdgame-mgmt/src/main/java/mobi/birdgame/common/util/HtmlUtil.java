package mobi.birdgame.common.util;

/**
 * Html工具类
 * Created by zhouwei on 2016/9/28.
 * @author zhouwei
 */
public class HtmlUtil {
    public static final String TAG_REG = "<([^>]*)>";

    /**
     * 去除字符串中的html标签，只剩下text
     *  TODO 还是存在在表中如果有“>”，去除错误的问题
     * @param source
     * @return
     */
    public static String removeHtmlTags(String source){
        return source.replaceAll(TAG_REG,"");
    }

    public static void main(String[] args){
        System.out.println(removeHtmlTags("<p><br><img onclick=\"if(this.width>screen.width-461) window.open('http://images2.static-bluray.com/movies/covers/29731_front.jpg');\" border=\"0\" alt=\"\" src=\"http://images2.static-bluray.com/movies/covers/29731_front.jpg\"><br><br>◎译　　名　阿特拉斯耸耸肩：第一部/阿特拉斯耸耸肩/地球颤栗<br>◎片　　名　Atlas Shrugged Part 1<br>◎年　　代　2011<br>◎国　　家　美国<br>◎类　　别　剧情/神秘/科幻<br>◎语　　言　英语<br>◎字　　幕　中英双字<br>◎IMDB评分 5.6/10 (2,765 votes)<br>◎文件格式　BD-RMVB<br>◎视频尺寸　1024 x 576<br>◎文件大小　1CD<br>◎片　　长　97 Mins<br>◎导　　演　保罗·约翰逊 Paul Johansson<br>◎主　　演　泰勒·席林 Taylor Schilling&nbsp; ....Dagny Taggart<br>　　　　　　格兰特·鲍尔 Grant Bowler&nbsp; ....Henry Rearden<br>　　　　　　马修·马斯登 Matthew Marsden&nbsp; ....James Taggart<br>　　　　　　保罗·约翰逊 Paul Johansson&nbsp; ....John Galt<br>　　　　　　迈克尔·奥吉弗 Michael O'Keefe&nbsp; ....Hugh Akston<br>　　　　　　艾迪·盖瑟吉 Edi Gathegi&nbsp; ....Eddie Willers<br>　　　　　　Jsu Garcia&nbsp; ....Francisco D'Anconia<br>　　　　　　Graham Beckel&nbsp; ....Ellis Wyatt<br>　　　　　　Patrick Fischler&nbsp; ....Paul Larkin<br>　　　　　　迈克尔·勒纳 Michael Lerner&nbsp; ....Wesley Mouch<br>　　　　　　Annabelle Gurwitch&nbsp; ....Reporter #2<br>　　　　　　乔·鲍里托 Jon Polito&nbsp; ....Orren Boyle<br>　　　　　　Armin Shimerman&nbsp; ....Dr. Potter<br>　　　　　　Rebecca Wisocky&nbsp; ....Lillian Rearden<br>　　　　　　杰弗瑞·佩尔森 Geoffrey Pierson&nbsp; ....Midas Mulligan<br>　　　　　　Christina Pickles&nbsp; ....Mother Rearden<br>　　　　　　Ethan Cohn&nbsp; ....Owen Kellogg<br>　　　　　　Navid Negahban&nbsp; ....Dr. Robert Stadler<br>　　　　　　Joel McKinnon Miller&nbsp; ....Herbert Mowen<br>　　　　　　Frank Cassavetes&nbsp; ....Bum<br>　　　　　　Sylva Kelegian&nbsp; ....Ivy Starnes<br>　　　　　　Neill Barry&nbsp; ....Phillip Rearden<br>　　　　　　Olivia Guerrero&nbsp; ....Waitress<br>　　　　　　Mel Fair&nbsp; ....Newscaster<br>　　　　　　Daisy McCrackin&nbsp; ....Clerk<br>　　　　　　Mandy June Turpin&nbsp; ....Newscaster<br>　　　　　　Rob Brownstein&nbsp; ....Eugene Lawson<br>　　　　　　Matt O'Toole&nbsp; ....Brenden Brady<br>　　　　　　Mercedes Connor&nbsp; ....Cherryl Brooks<br>　　　　　　Kim Swennen&nbsp; ....Newscaster<br>　　　　　　Dave Goryl&nbsp; ....Jay Knight<br>　　　　　　January Welsh&nbsp; ....Reporter<br>　　　　　　Christopher Karl Johnson&nbsp; ....Senator at press conference<br>　　　　　　Katherine M. O'Connor&nbsp; ....Senator at press conference<br>　　　　　　Marissa Welsh&nbsp; ....Ballroom Dancer<br>　　　　　　Nikki Klecha&nbsp; ....Gwen Ives<br>　　　　　　Jack Milo&nbsp; ....Richard McNamara<br>　　　　　　Craig Tsuyumine&nbsp; ....Reporter #1<br>　　　　　　Christopher Mur&nbsp; ....Male Newscaster<br>　　　　　　Travis Seaborn&nbsp; ....Bartender<br>　　　　　　Jan Morris&nbsp; ....Female Newscaster<br>　　　　　　Jeff Cockey&nbsp; ....Bartender<br>　　　　　　Latasha Muhammad&nbsp; ....Reporter / Couple Extra<br>　　　　　　Maia Tarin&nbsp; ....Joy<br>　　　　　　Ron Provencal&nbsp; ....Ballroom Dancer<br>　　　　　　Clay Bunker&nbsp; ....NNT Reporter (uncredited)<br>　　　　　　Paul Edney&nbsp; ....Bicyclist (uncredited)<br>　　　　　　Sarah Batsheva Graham&nbsp; ....Depressed Worker (uncredited)<br>　　　　　　David Dustin Kenyon&nbsp; ....Party Guest (uncredited)<br>　　　　　　Sarah Leners&nbsp; ....Ballroom Guest (uncredited)<br>　　　　　　John H. Tobin&nbsp; ....Corporate shark (uncredited)<br><br>◎简　　介　<br><br>　　在一个家族传承的塔格特铁路公司，现任总裁为家族长子詹姆斯·塔格特，副总裁是达格妮·塔格特，兄妹俩为挽救公司岌岌可危的营运，想法与方法可说是南辕北辙，一位只是口头囔囔一些没有经过详细评估的墨西哥——圣赛巴斯蒂安支线的投资，却没有提出一丝一毫的规划与执行方案，常常以见不得别人成功的酸葡萄心态看待事情与诋毁他人；另一位是以“我是像一个饿疯了一样，去找任何一个能把事情做好的人！”为挽救塔格特铁路公司，与爱迪·威勒斯孜孜�L�L、焚膏继晷的努力工作，为顺利完成里约诺特的铁路支线，找上里尔登钢铁的汉克·里尔登合作，采用里尔登刚发明的新合金当铁轨，没想到成功营造出里约诺特的铁路支线，却是另一个不幸的开端。<br><br>幕后制作<br><br>　　《阿特拉斯耸耸肩》是上世纪美国著名哲学家、小说家安·兰德的代表巨著，这位俄裔美籍小说家推崇理性，认为人的最高美德便是理性。她不顾传统舆论的偏见，力倡个人主义，认为不能使个人利益得到最大伸张的社会，就不是理想社会。她的客观主义哲学自上世纪50年代起便风靡美国校园，影响了几代美国人，她本人也成为美国青年崇拜的偶像。<br><br>　　《阿特拉斯耸耸肩》是她最著名的一本小说，曾在美国社会产生巨大影响。书中宣扬金钱至上的思想，探讨了理性利己主义的道德性。1957年刚刚出版曾遭遇社会恶评，但却异常畅销，在美国的销售量仅次于《圣经》，并影响了当时社会的很多知识分子，甚至成为美国学生必读的课外书籍。<br><br><img onclick=\"if(this.width>screen.width-461) window.open('http://img.album.pchome.net/54/84/49/21/1a830a8a44544d84fbf90e151d451717.jpg');\" border=\"0\" alt=\"\" src=\"http://img.album.pchome.net/54/84/49/21/1a830a8a44544d84fbf90e151d451717.jpg\"> <br><img onclick=\"if(this.width>screen.width-461) window.open('http://img.album.pchome.net/54/84/49/21/ebec6ab0140e2961138576eb6b7720ca.jpg');\" border=\"0\" alt=\"\" src=\"http://img.album.pchome.net/54/84/49/21/ebec6ab0140e2961138576eb6b7720ca.jpg\"></p>"));
    }
}
