package mobi.birdgame.common.util;


import java.util.List;

/**
 * 分页工具类
 *
 * @param <T>
 */
public final class PaginationSupport<T>  {
	private static final long serialVersionUID = 5630859931214794398L;
	
	public final static int DEFAULT_PAGESIZE = 20; //默认分页大小
	public final static int MAX_PAGESIZE = 100; //最大分页大小
	
	private int pageSize = DEFAULT_PAGESIZE;
	private int totalCount;
	private int totalPages;
	private List<T> items;
	private int startIndex;
	private int realSize;
	private int currentPage;
	
	public PaginationSupport(List<T> items, int totalCount) {
		this.items = items;
		this.pageSize = DEFAULT_PAGESIZE;
		this.totalCount = totalCount;
		this.startIndex = 0;
		this.currentPage = 1;
		this.totalPages = (totalCount%this.pageSize == 0)? (totalCount/this.pageSize):(totalCount/this.pageSize+1);
		if(items == null || items.isEmpty()){
			realSize = 0;
		}else{
			realSize = items.size();
		}
	}
	
	public PaginationSupport(List<T> items, int totalCount, int pageSize, int startIndex, int currentPage) {
		this.items = items;
		if(pageSize <= 0 || pageSize > MAX_PAGESIZE){
			this.pageSize = DEFAULT_PAGESIZE;
		}else{
			this.pageSize = pageSize;
		}
		this.totalCount = totalCount;
		
		if(startIndex >= totalCount){
			this.startIndex = totalCount - 1;
		}else{
			this.startIndex = startIndex;
		}
		if(this.startIndex < 0){
			this.startIndex = 0;
		}
		
		this.currentPage = currentPage;
		this.totalPages = (totalCount%this.pageSize == 0)? (totalCount/this.pageSize):(totalCount/this.pageSize+1);
		if(items == null || items.isEmpty()){
			realSize = 0;
		}else{
			realSize = items.size();
		}
	}

	public int getPageSize() {
		return pageSize;
	}

	public int getTotalCount() {
		return totalCount;
	}

	public List<T> getItems() {
		return items;
	}

	public int getStartIndex() {
		return startIndex;
	}

	public int getTotalPages() {
		return totalPages;
	}

	public int getRealSize() {
		return realSize;
	}
	public int getCurrentPage() {
		return currentPage;
	}
	public void setCurrentPage(int currentPage) {
		this.currentPage = currentPage;
	}
}
