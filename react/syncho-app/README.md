
# 📁 React 프로젝트 파일 구조

이 문서는 React 프로젝트에서 사용된 **파일 구조**와 각 폴더의 역할을 설명합니다. 유지보수와 확장성을 고려한 체계적인 구조입니다.

---

## 📂 `src/`

`src/` 디렉토리는 React 애플리케이션의 모든 소스 코드를 포함합니다. 아래는 주요 폴더와 그 역할을 설명한 구조입니다:

```
src/
├── assets/        # 정적 파일(이미지, 폰트, JSON 데이터 등)
├── components/    # 재사용 가능한 UI 컴포넌트
├── hooks/         # 커스텀 React 훅
├── pages/         # 라우팅에 사용되는 페이지 컴포넌트
├── styles/        # 전역 및 공통 스타일 파일(CSS/SCSS)
└── App.js         # 애플리케이션의 루트 컴포넌트
```

---

### 📂 `assets/`
**정적 리소스(Static Assets)**를 저장하는 디렉토리입니다.  
이미지, 폰트, JSON 파일, 오디오/비디오 파일 등을 포함합니다.

예시:
```
assets/
├── images/        # 이미지 파일
│   ├── logo.png
│   └── banner.jpg
├── fonts/         # 폰트 파일
│   └── Roboto.ttf
└── data/          # JSON 데이터
    └── config.json
```

---

### 📂 `components/`
재사용 가능한 **UI 컴포넌트**를 저장하는 디렉토리입니다.  
버튼, 모달, 카드 등 다양한 컴포넌트를 이곳에 관리합니다.

예시:
```
components/
├── Button/
│   ├── Button.js
│   ├── Button.css
│   └── index.js
├── Modal/
│   ├── Modal.js
│   └── Modal.css
└── Navbar/
    ├── Navbar.js
    ├── Navbar.css
    └── index.js
```

---

### 📂 `hooks/`
React의 **로직 재사용**을 위한 커스텀 훅(Custom Hooks)을 저장합니다.  
API 호출, 상태 관리, 디바운스 처리 등 로직을 캡슐화합니다.

예시:
```
hooks/
├── useFetch.js       # 데이터 Fetch 훅
├── useLocalStorage.js # 로컬 스토리지 관리 훅
└── useDebounce.js    # 디바운스 처리 훅
```

---

### 📂 `pages/`
React 애플리케이션의 **페이지 컴포넌트**를 저장하는 디렉토리입니다.  
각 페이지는 라우팅과 연결되며, 내부적으로 여러 컴포넌트를 조합합니다.

예시:
```
pages/
├── Home/
│   ├── Home.js
│   └── Home.css
├── About/
│   ├── About.js
│   └── About.css
└── Contact/
    ├── Contact.js
    └── Contact.css
```

---

### 📂 `styles/`
프로젝트 전역 스타일이나 공통 스타일 파일을 저장합니다.  
CSS 변수, 초기화 파일, 테마 관련 스타일 등을 포함합니다.

예시:
```
styles/
├── reset.css       # 브라우저 초기화 스타일
├── variables.css   # CSS 변수(색상, 폰트 크기 등)
├── theme.css       # 테마 스타일
└── mixins.css      # SCSS Mixin (선택적)
```

---

### 📄 `App.js`
애플리케이션의 **최상위 컴포넌트**로, 전체 UI의 루트 역할을 합니다.  
다른 컴포넌트와 페이지를 불러와 애플리케이션 구조를 정의합니다.

---

## 😊✨
