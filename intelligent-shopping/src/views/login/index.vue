<template>
  <div class="login">
    <van-nav-bar title="会员登录" left-arrow @click-left="$router.go(-1)" />
    <div class="container">
      <div class="title">
        <h3>手机号登录</h3>
        <p>未注册的手机号登录后将自动注册</p>
      </div>

      <div class="form">
        <div class="form-item">
          <input class="inp" v-model="mobile" maxlength="11" placeholder="请输入手机号码" type="text">
        </div>
        <div class="form-item">
          <input class="inp" v-model="picCode" maxlength="5" placeholder="请输入图形验证码" type="text">
          <img v-if="picUrl" :src=picUrl alt="" @click="getPicCode">
        </div>
        <div class="form-item">
          <input class="inp" v-model="msgCode" placeholder="请输入短信验证码" type="text">
          <button @click=getCode()>
            {{ second === totalSecond ? '获取验证码' : second + `秒后重新发送`}}
            </button>
        </div>
      </div>

      <div class="login-btn" @click="login">登录</div>
    </div>
  </div>
</template>

<script>
import { getPicCode, getMsgCode, codeLogin } from '@/api/login'
export default {
  name: 'LoginPage',
  created () {
    this.getPicCode()
  },
  data () {
    return {
      picKey: '',
      picUrl: '',
      totalSecond: 60,
      second: 60,
      tiner: null,
      mobile: '13407348116',
      picCode: '',
      msgCode: '246810'
    }
  },
  methods: {
    async getPicCode () {
      const { data: { base64, key } } = await getPicCode()
      this.picUrl = base64
      this.pickey = key
    },
    // 校验输入框内容
    validFn () {
      if (!/^1[3-9]\d{9}$/.test(this.mobile)) {
        this.$toast('请输入正确的手机号')
        return false
      }
      if (!/^\w{4}$/.test(this.picCode)) {
        this.$toast('请输入正确的图形验证码')
        return false
      }
      return true
    },
    async getCode () {
      if (!this.validFn()) {
        return
      }
      //   if (!this.timer && this.second === this.totalSecond) {
      // 开启倒计时
      this.timer = setInterval(() => {
        this.second--

        if (this.second < 1) {
          clearInterval(this.timer)
          this.timer = null
          this.second = this.totalSecond
        }
      }, 1000)

      const res = await getMsgCode(this.picCode, this.picKey, this.mobile)
      // 发送请求，获取验证码
      console.log(res)
      this.$toast('发送成功，请注意查收')
    },
    // 登录逻辑
    async login () {
      if (!this.validFn) {
        return
      }
      // 验证短信验证码
      if (!/^\d{6}$/.test(this.msgCode)) {
        this.$toast('请输入正确的短信验证码')
        return
      }
      // 发送请求
      const res = await codeLogin(this.mobile, this.msgCode)
      // 将userInfo数据存入vuex仓库
      this.$store.commit('user/setUserInfo', res.data)
      console.log(this.$store.getters.token)
      // 将页面跳转到首页 如果有会跳地址则跳到回跳地址
      // this.$router.push('/')
      const url = this.$route.query.backUrl || '/'
      this.$router.replace(url)
      // 提示用户登录成功
      this.$toast('登录成功')
    }
  },
  destroyed () {
    clearInterval(this.timer)
  }
}
</script>

<style lang="less" scoped>
.container {
  padding: 49px 29px;

  .title {
    margin-bottom: 20px;
    h3 {
      font-size: 26px;
      font-weight: normal;
    }
    p {
      line-height: 40px;
      font-size: 14px;
      color: #b8b8b8;
    }
  }

  .form-item {
    border-bottom: 1px solid #f3f1f2;
    padding: 8px;
    margin-bottom: 14px;
    display: flex;
    align-items: center;
    .inp {
      display: block;
      border: none;
      outline: none;
      height: 32px;
      font-size: 14px;
      flex: 1;
    }
    img {
      width: 94px;
      height: 31px;
    }
    button {
      height: 31px;
      border: none;
      font-size: 13px;
      color: #cea26a;
      background-color: transparent;
      padding-right: 9px;
    }
  }

  .login-btn {
    width: 100%;
    height: 42px;
    margin-top: 39px;
    background: linear-gradient(90deg,#ecb53c,#ff9211);
    color: #fff;
    border-radius: 39px;
    box-shadow: 0 10px 20px 0 rgba(0,0,0,.1);
    letter-spacing: 2px;
    display: flex;
    justify-content: center;
    align-items: center;
  }
}
</style>
