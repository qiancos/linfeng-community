<template>
  <el-dialog
    :title="!dataForm.id ? '新增' : '修改'"
    :close-on-click-modal="false"
    :visible.sync="visible"
  >
    <el-form
      :model="dataForm"
      ref="dataForm"
      @keyup.enter.native="dataFormSubmit()"
      label-width="80px"
    >
      <el-form-item label="浏览量" prop="readCount">
        <el-input v-model="dataForm.readCount" placeholder="浏览量"></el-input>
      </el-form-item>
      <el-form-item label="平台置顶" prop="postTop">
        <el-radio-group v-model="dataForm.postTop">
          <el-radio :label="0">否</el-radio>
          <el-radio :label="1">是</el-radio>
        </el-radio-group>
      </el-form-item>
    </el-form>
    <span slot="footer" class="dialog-footer">
      <el-button @click="visible = false">取消</el-button>
      <el-button type="primary" @click="dataFormSubmit()">确定</el-button>
    </span>
  </el-dialog>
</template>

<script>
export default {
  data() {
    return {
      visible: false,
      dataForm: {
        id: 0,
        uid: "",
        topicId: "",
        discussId: "",
        voteId: "",
        title: "",
        content: "",
        media: "",
        readCount: "",
        postTop: "",
        type: "",
        address: "",
        longitude: "",
        latitude: "",
        createTime: "",
        status: "",
      },
    };
  },
  methods: {
    init(id) {
      this.dataForm.id = id || 0;
      this.visible = true;
      this.$nextTick(() => {
        this.$refs["dataForm"].resetFields();
        if (this.dataForm.id) {
          this.$http({
            url: this.$http.adornUrl(`/admin/post/info/${this.dataForm.id}`),
            method: "get",
            params: this.$http.adornParams(),
          }).then(({ data }) => {
            if (data && data.code === 0) {
              this.dataForm.uid = data.post.uid;
              this.dataForm.topicId = data.post.topicId;
              this.dataForm.discussId = data.post.discussId;
              this.dataForm.voteId = data.post.voteId;
              this.dataForm.title = data.post.title;
              this.dataForm.content = data.post.content;
              this.dataForm.media = data.post.media;
              this.dataForm.readCount = data.post.readCount;
              this.dataForm.postTop = data.post.postTop;
              this.dataForm.type = data.post.type;
              this.dataForm.address = data.post.address;
              this.dataForm.longitude = data.post.longitude;
              this.dataForm.latitude = data.post.latitude;
              this.dataForm.createTime = data.post.createTime;
              this.dataForm.status = data.post.status;
            }
          });
        }
      });
    },
    // 表单提交
    dataFormSubmit() {
      this.$refs["dataForm"].validate((valid) => {
        if (valid) {
          this.$http({
            url: this.$http.adornUrl(
              `/admin/post/${!this.dataForm.id ? "save" : "update"}`
            ),
            method: "post",
            data: this.$http.adornData({
              id: this.dataForm.id || undefined,
              uid: this.dataForm.uid,
              topicId: this.dataForm.topicId,
              discussId: this.dataForm.discussId,
              voteId: this.dataForm.voteId,
              title: this.dataForm.title,
              content: this.dataForm.content,
              media: this.dataForm.media,
              readCount: this.dataForm.readCount,
              postTop: this.dataForm.postTop,
              type: this.dataForm.type,
              address: this.dataForm.address,
              longitude: this.dataForm.longitude,
              latitude: this.dataForm.latitude,
              createTime: this.dataForm.createTime,
              status: this.dataForm.status,
            }),
          }).then(({ data }) => {
            if (data && data.code === 0) {
              this.$message({
                message: "操作成功",
                type: "success",
                duration: 1500,
                onClose: () => {
                  this.visible = false;
                  this.$emit("refreshDataList");
                },
              });
            } else {
              this.$message.error(data.msg);
            }
          });
        }
      });
    },
  },
};
</script>
