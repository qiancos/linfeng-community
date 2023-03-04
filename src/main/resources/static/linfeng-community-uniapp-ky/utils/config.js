const miniappName = "林风社交论坛"; //分享时的标题
const shareH5Url = "https://www.linfeng.tech/#/"; //H5分享路径


//本地环境配置
// const baseUrl = "localhost:8080";
// const domain = 'http://' + baseUrl + "/app/"; 

//线上环境配置
<<<<<<< HEAD
const baseUrl = "wxapi.linfeng.tech";
const domain = 'https://' + baseUrl + "/app/"; 
=======
// const baseUrl = "";
// const domain = 'https://' + baseUrl + "/app/"; 
>>>>>>> 85ba2abf696189e458edd77880b587e2e8b9d91c



export default {
	baseUrl: baseUrl,
	domain: domain,
	miniappName: miniappName,
	shareH5Url: shareH5Url
}
