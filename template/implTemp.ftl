/**
 * <p>ClassName:${voName}</p>
 * <p>Title:</p>
 * <p>Description:${voName}实现类</p>
 * <p>Copyright:Copyright (c) 2010</p>
 * <p>Company:易泓咨询管理公司</p>
 * <p>Date:${date}</p>
 * <p>Modify:</p>
 * <p>Bug:</p>
 * <p>@author ${name} </p>
 * <p>@version 1.0</p>	 
 * 
 */
@Service
public class ${voName}Impl implements I${voName} {

    @Autowired
	GeneralDAO dao;
	
    //查询 by jyy
    @Override
	public void query${voName}List(${voName} vo,HttpContext context){
	DtPvUsers user = (DtPvUsers)context.getUserLoginInfo().getInfo();
		vo.setCOMPANY_ID(user.getCOMPANY_ID());
		List<${voName}> list=(List<${voName}>) dao.queryForList("${voName}Impl.query${voName}List",vo);
		context.addResultList(list);
	}
	//保存 by jyy
	@Override
	public void save${voName}(${voName} vo,HttpContext context){
	 Date date = CommonService.getGeneralService().getSystemDate();
		  if(Constants.ACTION_UPDATE.equals(vo.getUPDATE_STATUS())){
			  vo.setUPDATED_BY(user.getUSER_ID());
			  vo.setUPDATED_TIME(date);
			  Integer updateCount = dao.updateObject("${voName}Impl.update${voName}", vo);
			  if(Constants.CONST_Z.equals(updateCount)) {
			  throw new OldDataException();
			  }
			}else if(Constants.ACTION_DELETE.equals(vo.getUPDATE_STATUS())){
			  dao.deleteObject("${voName}Impl.delete${voName}", vo);
			}else if(Constants.ACTION_INSERT.equals(vo.getUPDATE_STATUS())){
				vo.setINSURANCE_ID(CommonService.getSeqenceService().getSequenceNo(user.getCOMPANY_ID(), xx));
				vo.setCOMPANY_ID(user.getCOMPANY_ID());
				vo.setCREATED_BY(user.getUSER_ID());
				vo.setCREATED_TIME(date);
				dao.insertObject("${voName}Impl.insert${voName}", vo);
			}
		 context.addResultObject(vo);
	}
	//查询明细 by jyy
	@Override
	public void query${voName}Detail(${voName} vo,HttpContext context){
	DtPvUsers user = (DtPvUsers) context.getUserLoginInfo().getInfo();
		vo.setCOMPANY_ID(user.getCOMPANY_ID());
		//你的代码
	    context.addResultObject(vo);
		
	}
	}