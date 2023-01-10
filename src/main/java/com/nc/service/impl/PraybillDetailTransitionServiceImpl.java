package com.nc.service.impl;

import com.nc.entity.Praybill;
import com.nc.entity.PraybillB;
import com.nc.entity.PraybillDetail;
import com.nc.entity.PraybillStatusEnum;
import com.nc.mapper.*;
import com.nc.service.IPraybillDetailTransitionService;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;


@Service
public class PraybillDetailTransitionServiceImpl implements IPraybillDetailTransitionService {

    @Resource
    private DeptVMapper deptVMapper;

    @Resource
    private MaterialMapper materialMapper;

    @Resource
    private MeasdocMapper measdocMapper;

    @Resource
    private SupplierMapper supplierMapper;

    @Resource
    private ProjectMapper projectMapper;

    @Resource
    private DefdocMapper defdocMapper;

    @Resource
    private BilltypeMapper billtypeMapper;

    @Resource
    private PsndocMapper psndocMapper;


    /**
     * 将praybillB 转换成 praybill
     * @param praybillBs
     * @param praybill
     * @return 转换好的PraybillDetails
     */
    @Override
    public List<PraybillDetail> translateFromPraybillBsToPraybillDetails(List<PraybillB> praybillBs,
                                                                         Praybill praybill) {

        List<PraybillDetail> praybillDetails = new ArrayList<PraybillDetail>();

        for (PraybillB praybillB : praybillBs) {

            PraybillDetail praybillDetail = null;
            //new praybillDetail
            praybillDetail = new PraybillDetail();

            //组装praybillDetail来源于请购单表体的数据
            praybillDetail = assemblePraybillDetailFromPraybillB(praybillDetail, praybillB);

            //组装praybillDetail来源于请购单表头的数据
            praybillDetail = assemblePraybillDetailFromPraybill(praybillDetail, praybill);

            praybillDetails.add(praybillDetail);

        }

        return praybillDetails;

    }


    /**
     * //组装praybillDetail来源于请购单表体的数据
     * @param praybillDetail
     * @param praybillB
     * @return
     */
    public PraybillDetail assemblePraybillDetailFromPraybillB(PraybillDetail praybillDetail, PraybillB praybillB) {

        //主键
        String pk_praybill_b = praybillB.getPK_PRAYBILL_B();

        praybillDetail.setPK_PRAYBILL_B(pk_praybill_b);

        //拼需求部门
        praybillDetail = assembleReqdept(praybillDetail, praybillB);

        //拼行号
        String crowno = praybillB.getCROWNO();

        praybillDetail.setCROWNO(crowno);

        //拼物料主键、编码、名称
        praybillDetail = assembleMaterial(praybillDetail, praybillB);

        //拼计量单位
        praybillDetail = assembleMeasdoc(praybillDetail, praybillB);

        //拼原始缺货数
        String vbdef9 = praybillB.getVBDEF9();
        if(null!=vbdef9 && !"~".equalsIgnoreCase(vbdef9)){
            praybillDetail.setVBDEF9(vbdef9);
        }

        //拼数量
        String nastnum = praybillB.getNASTNUM();

        praybillDetail.setNASTNUM(nastnum);

        //拼含税单价
        String ntaxprice = praybillB.getNTAXPRICE();

        praybillDetail.setNTAXPRICE(ntaxprice);

        //价税合计
        String ntaxmny = praybillB.getNTAXMNY();

        praybillDetail.setNTAXMNY(ntaxmny);

        //需求日期
        String dreqdate = praybillB.getDREQDATE();

        if(null!=dreqdate && !"~".equalsIgnoreCase(dreqdate)){

            praybillDetail.setDREQDATE(dreqdate);
        }

        //建议订货日期
        String dsuggestdate = praybillB.getDSUGGESTDATE();

        if (null!=dsuggestdate && !"~".equalsIgnoreCase(dsuggestdate)){
            praybillDetail.setDSUGGESTDATE(dsuggestdate);
        }

        //建议外包单位 pk code name
        praybillDetail = assembleSupplier(praybillDetail, praybillB);

        //选择外包单位的理由
        String vbmemo = praybillB.getVBMEMO();

        if(null!=vbmemo && !"~".equalsIgnoreCase(vbmemo)){
            praybillDetail.setVBMEMO(vbmemo);
        }

        //项目 pk、code、name、项目代号、开工单号
        praybillDetail = assembleProject(praybillDetail, praybillB);

        //拼生产部门主键、编码
        assembleProduceDept(praybillDetail, praybillB);

        //拼质检分类
        praybillDetail  = assembleQI(praybillDetail, praybillB);

        //重排日期
        String vbdef16 = praybillB.getVBDEF16();
        if(null!=vbdef16 && !"~".equalsIgnoreCase(vbdef16)){
            praybillDetail.setVBDEF16(vbdef16);
        }

        //预留数量
        String vbdef19 = praybillB.getVBDEF19();
        if(null!=vbdef19 && !"~".equalsIgnoreCase(vbdef19)){
            praybillDetail.setVBDEF19(vbdef19);
        }

        //预留转移数量
        String vbdef20 = praybillB.getVBDEF20();

        if(null!=vbdef20 && !"~".equalsIgnoreCase(vbdef20)){
            praybillDetail.setVBDEF20(vbdef20);
        }
        //询报价单号
        String vbdef21 = praybillB.getVBDEF21();
        if(null!=vbdef21 && !"~".equalsIgnoreCase(vbdef21)){
            praybillDetail.setVBDEF21(vbdef21);
        }

        //价格审批单号
        String vbdef22 = praybillB.getVBDEF22();
        if(null!=vbdef22 && !"~".equalsIgnoreCase(vbdef22)){
            praybillDetail.setVBDEF22(vbdef22);
        }

        //采购订单号
        String vbdef23 = praybillB.getVBDEF23();
        if(null!=vbdef23 && !"~".equalsIgnoreCase(vbdef23)){
            praybillDetail.setVBDEF23(vbdef23);
        }

        //生成订单次数
        String vbdef24 = praybillB.getVBDEF24();
        if(null!=vbdef24 && !"~".equalsIgnoreCase(vbdef24)){
            praybillDetail.setVBDEF24(vbdef24);
        }

        return praybillDetail;
    }

    public PraybillDetail assembleProduceDept(PraybillDetail praybillDetail, PraybillB praybillB) {

        String vfree1 = praybillB.getVFREE1();

        if (null!=vfree1 && !"~".equalsIgnoreCase(vfree1)){

            praybillDetail.setPRODUCE_DEPTID(vfree1);

            String code = deptVMapper.selectDeptVCodeByPk(vfree1);

            praybillDetail.setPRODUCE_DEPTCODE(code);

        }

        return praybillDetail;

    }

    public PraybillDetail assembleQI(PraybillDetail praybillDetail, PraybillB praybillB) {

        String vbdef17 = praybillB.getVBDEF17();

        if (null!=vbdef17 && !"~".equalsIgnoreCase(vbdef17)){

            praybillDetail.setQI_CLASSIFICATIONID(vbdef17);

            //质检分类的code
            Map<String, Object> QICoding = defdocMapper.selectQICodeByPk(vbdef17);

            Object code = QICoding.get("CODE");

            if (null!=code){

                String QICode = (String)code;

                praybillDetail.setQI_CLASSIFICATIONCODE(QICode);

            }

        }

        return praybillDetail;
    }

    /**
     * 拼项目code name 、项目代号、开工单号
     * @param praybillDetail
     * @param praybillB
     * @return
     */
    public PraybillDetail assembleProject(PraybillDetail praybillDetail, PraybillB praybillB) {

        String cprojectid = praybillB.getCPROJECTID();

        if (null!=cprojectid && !"~".equalsIgnoreCase(cprojectid)){

            praybillDetail.setCPROJECTID(cprojectid);

            Map<String, Object> projectCodeAndNameAndSymbolAndNumber
                    = projectMapper.selectCodeAndNameAndSymbolAndNumberByPk(cprojectid);

            Object project_code = projectCodeAndNameAndSymbolAndNumber.get("PROJECT_CODE");

            if (null!=project_code){

                String projectCode = (String)project_code;

                praybillDetail.setCPROJECTCODE(projectCode);

            }

            Object project_name = projectCodeAndNameAndSymbolAndNumber.get("PROJECT_NAME");

            if (null!=project_name){

                String projectName =  (String)project_name;

                praybillDetail.setCPROJECTNAME(projectName);
            }

            Object def1 = projectCodeAndNameAndSymbolAndNumber.get("DEF1");

            if (null!=def1){

                String projectSymbol = (String)def1;

                praybillDetail.setCPROJECTID_DEF1(projectSymbol);

            }

            Object def16 = projectCodeAndNameAndSymbolAndNumber.get("DEF16");

            if (null!=def16){

                String projectNumber = (String)def16;

                praybillDetail.setCPROJECTID_DEF16(projectNumber);

            }

        }

        return praybillDetail;

    }

    public PraybillDetail assembleSupplier(PraybillDetail praybillDetail,
                                            PraybillB praybillB) {

        String pk_suggestsupplier = praybillB.getPK_SUGGESTSUPPLIER();

        if (null!=pk_suggestsupplier&&!"~".equalsIgnoreCase(pk_suggestsupplier)){

            praybillDetail.setPK_SUGGESTSUPPLIER(pk_suggestsupplier);

            Map<String, Object> supplierCodeAndName = supplierMapper.selectSupplierCodeAndName(pk_suggestsupplier);

            Object code = supplierCodeAndName.get("CODE");

            if (null!=code){

                String supplierCode = (String)code;

                praybillDetail.setCODE_SUGGESTSUPPLIER(supplierCode);

            }

            Object name = supplierCodeAndName.get("NAME");

            if (null!=name){

                String supplierName =  (String)name;

                praybillDetail.setNAME_SUGGESTSUPPLIER(supplierName);

            }


        }

        return  praybillDetail;
    }


    public PraybillDetail assembleMeasdoc(PraybillDetail praybillDetail, PraybillB praybillB) {

        //计量单位主键
        String castunitid = praybillB.getCASTUNITID();

        if (null!=castunitid && !"~".equalsIgnoreCase(castunitid)){

            praybillDetail.setPK_MEASDOC(castunitid);

            Map<String, Object> measCodeAndName = measdocMapper.selectMeasCodeAndNameByPk(castunitid);

            Object code = measCodeAndName.get("CODE");

            if (null!=code){

               String measCode = (String)code;

               praybillDetail.setCODE_MEASDOC(measCode);

            }

            Object name = measCodeAndName.get("NAME");

            if(null!=name){

                String measName = (String)name;

                praybillDetail.setNAME_MEASDOC(measName);

            }

        }

        return praybillDetail;

    }

    public PraybillDetail assembleMaterial(PraybillDetail praybillDetail, PraybillB praybillB) {


        //物料主键
        String pk_material = praybillB.getPK_MATERIAL();

        if (null!=pk_material && !"~".equalsIgnoreCase(pk_material)){

            //set pk_material
            praybillDetail.setPK_MATERIAL(pk_material);

            //得到物料的code和name
            Map<String, Object> codeAndNameAndVersionAndSpec =
                    materialMapper.selectMaterialCodeAndNameAndVersionAndSpecByPk(pk_material);

            Object code = codeAndNameAndVersionAndSpec.get("CODE");

            if (null!=code){

                String materialCode = (String)code;
                //set code
                praybillDetail.setCODE_MATERIAL(materialCode);
            }

            Object name = codeAndNameAndVersionAndSpec.get("NAME");

            if (null!=name){

               String materialName =  (String)name;

                //set name
                praybillDetail.setNAME_MATERIAL(materialName);

            }

            Object version = codeAndNameAndVersionAndSpec.get("VERSION");

            if (null!=version){

                java.math.BigDecimal materialVersion = (java.math.BigDecimal)version;

                praybillDetail.setVERSION_MATERIAL(materialVersion.toString());

            }

            Object spec = codeAndNameAndVersionAndSpec.get("MATERIALSPEC");

            if (null!=spec){

                String materialSpec =  (String)spec;

                praybillDetail.setSPEC_MATERIAL(materialSpec);

            }


        }

        return praybillDetail;

    }

    public PraybillDetail assembleReqdept(PraybillDetail praybillDetail, PraybillB praybillB) {

        //需求部门 DeptVMapper
        String pk_reqdept_v = praybillB.getPK_REQDEPT_V();

        String deptVCode = null;

        //查询需求部门 code
        if (null!=pk_reqdept_v && !"~".equalsIgnoreCase(pk_reqdept_v)){
          deptVCode = deptVMapper.selectDeptVCodeByPk(pk_reqdept_v);
        }

        praybillDetail.setPK_PLANDEPT(pk_reqdept_v);

        praybillDetail.setCODE_PLANDEPT(deptVCode);

        return praybillDetail;

    }


    /**
     * //组装praybillDetail来源于请购单表头的数据
     * @param praybillDetail
     * @param praybill
     * @return
     */
    public PraybillDetail assemblePraybillDetailFromPraybill(PraybillDetail praybillDetail,
                                                             Praybill praybill) {
        //外包申请单号
        String vbillcode = praybill.getVBILLCODE();

        praybillDetail.setVBILLCODE(vbillcode);

        //外包申请日期
        String dbilldate = praybill.getDBILLDATE();

        praybillDetail.setDBILLDATE(dbilldate);

        //请购类型主键、编码
        praybillDetail = assembleBillType(praybillDetail, praybill);

        //申请人主键、编码
        praybillDetail  = assemblePlanPsn(praybillDetail, praybill);

        //单据状态枚举
        praybillDetail  = assembleBillStatus(praybillDetail, praybill);

        //外包类型
        praybillDetail  = assembleOutsourceType(praybillDetail, praybill);

        //订单号
        String vdef7 = praybill.getVDEF7();

        if (null!=vdef7 && !"~".equalsIgnoreCase(vdef7)){
            praybillDetail.setVDEF7(vdef7);
        }

        //外包内容
        String vmemo = praybill.getVMEMO();

        if (null!=vmemo && !"~".equalsIgnoreCase(vmemo)){
            praybillDetail.setVMEMO(vmemo);
        }

        //备注
        String vdef8 = praybill.getVDEF8();

        if(null!=vdef8 && !"~".equalsIgnoreCase(vdef8)){
            praybillDetail.setVDEF8(vdef8);
        }

        return praybillDetail;

    }

    public PraybillDetail assembleOutsourceType(PraybillDetail praybillDetail, Praybill praybill) {

        //外包类型
        String vdef5 = praybill.getVDEF5();

        if (null!=vdef5 && !"~".equalsIgnoreCase(vdef5)){

            praybillDetail.setPK_VDEF5(vdef5);

            Map<String, Object> outsourceCodeByPk = defdocMapper.selectOutsourceCodeByPk(vdef5);

            Object code = outsourceCodeByPk.get("CODE");

            if (null!=code){

                String outsourceCode = (String)code;

                praybillDetail.setCODE_VDEF5(outsourceCode);

            }

        }


        return praybillDetail;

    }

    public PraybillDetail assembleBillStatus(PraybillDetail praybillDetail, Praybill praybill) {

        String fbillstatus = praybill.getFBILLSTATUS();

        if (null!=fbillstatus && !"~".equalsIgnoreCase(fbillstatus)){

            praybillDetail.setFBILLSTATUS_NUMBER(fbillstatus);

            int status = Integer.parseInt(fbillstatus);

            switch (status){
                case 0:
                    praybillDetail.setFBILLSTATUS_ENGNAME(PraybillStatusEnum.FREE.toString());
                    break;
                case 1:
                    praybillDetail.setFBILLSTATUS_ENGNAME(PraybillStatusEnum.COMMIT.toString());
                    break;
                case 2:
                    praybillDetail.setFBILLSTATUS_ENGNAME(PraybillStatusEnum.APPROVING.toString());
                    break;
                case 3:
                    praybillDetail.setFBILLSTATUS_ENGNAME(PraybillStatusEnum.APPROVE.toString());
                    break;
                case 4:
                    praybillDetail.setFBILLSTATUS_ENGNAME(PraybillStatusEnum.NOPASS.toString());
                    break;
                default:
                    praybillDetail.setFBILLSTATUS_ENGNAME(PraybillStatusEnum.CLOSE.toString());
            }

        }

        return praybillDetail;

    }

    public PraybillDetail assemblePlanPsn(PraybillDetail praybillDetail, Praybill praybill) {

        //申请人主键、编码
        String pk_planpsn = praybill.getPK_PLANPSN();

        if (null!=pk_planpsn && !"~".equalsIgnoreCase(pk_planpsn)){

            praybillDetail.setPK_PLANPSN(pk_planpsn);

            Map<String, Object> psndocCodeById = psndocMapper.selectPsnCodeByPk(pk_planpsn);

            Object code = psndocCodeById.get("CODE");

            if (null!=code && !"~".equalsIgnoreCase((String) code)){

                String PsnCode =  (String)code;

                praybillDetail.setCODE_PLANPSN(PsnCode);

            }


        }

        return praybillDetail;

    }

    public PraybillDetail assembleBillType(PraybillDetail praybillDetail, Praybill praybill) {

        //请购类型主键、编码
        String ctrantypeid = praybill.getCTRANTYPEID();

        if (null!=ctrantypeid && !"~".equalsIgnoreCase(ctrantypeid)){

            praybillDetail.setCTRANTYPEID(ctrantypeid);

            String code = billtypeMapper.selectCodeByPk(ctrantypeid);

            if (null!=code && !"~".equalsIgnoreCase(code)){

                praybillDetail.setCTRANTYPECODE(code);

            }

        }

        return praybillDetail;

    }


}
