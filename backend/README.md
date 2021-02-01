# REST API

---

- ### 회원관리 [UserController]

  - 회원가입

    -  ##### _join(user_id, user_name, user_nickName, user_password, user_email, user_tel, user_type)_

      - 나머지 DB 속성 값들은 NULL 값으로 저장
      - 닉네임 초기 회원가입시 Fix값 (랜덤)
      - 가입일 now()
      - UserDB(user_about) : default "나를 소개해보세요!" -> DB 기본 설정

  - 회원탈퇴

    - ##### _leaveUser(user_id)_

    - ##### _checkPassword(user_id, [input]input_password)_ => T/F or "such"

      - Params - input_password : 비밀번호 확인 시 사용자가 입력한 비밀번호
      - 사용자 입력 비밀번호와 DB에 저장된 비밀번호와 일치하는지 검사

  - 사용자 정보 받아오기

    - ##### _getUserInfo(user_id)_ => UserDto

      - 비밀번호를 제외한 모든 정보 return

  - 회원정보수정

    - ##### _modifyUser(user_id, user_name, user_nickName, user_password, user_email, user_tel, user_birth, user_profile)_

      - user_id -> query문 조건절 용

    - ##### _modifyUserAbout(user_id, user_about)_

      

- ### 로그인 관리 (JWT) [LoginController]

  - 로그인

    - ##### _login(user_id, user_password)_

  - 로그아웃

    - ##### ~~_logout_~~

  - 아이디 찾기

    - ##### _findId(user_name, user_email)_  => user_id

  - 비밀번호 찾기

    - ##### _findPassword(user_id, user_email, used_tel)_  

      - User DB에 등록된 이메일로 비밀번호 전송



- ### 작가 관리  [ArtistController]

  - 작가 정보받아오기

    - ##### _getArtistInfo(artist_id)_  => ArtistDto

  - 작가 등록하기

    - ##### _changeArtist(artist_id, artist_bank, artist_account, artist_name, artist_nickName, artist_exp)_

      - artist_id = user_id

  - 작가 등록 해제하기

    - ##### _leaveArtist(artist_id)_



- ### 작품 관리 [WorkController]

  - 작품등록

    - ##### _addWork(work_artistId, work_title, work_desc, work_piece, work_rating)_

      - work DB 에서 work_cost($:좋아요 수) 는 default값으로 0 지정해줌
      - work DB 에서 work_id auto_increment로 설정해놓음

  - 작품수정

    - ##### _modifyWork(work_id, work_title, work_desc, work_piece, work_rating)_

  - 작품삭제

    - ##### _deleteWork(work_id)_

  - 작품 전체 목록 조회

    - ##### _getAllWork()_

  - 팔로우한 작가 작품 목록 조회

    - ##### _getFollowingWork(follow_userId)_

  - 작품 검색 - 해시태그 검색

    - ##### _searchByHashTag([List]input_HashTag_List)_

      - Front 단에서 input string으로 받은 단어(해시태그)들을 리스트로 저장해서 매개변수로 호출

  - 작품 상세조회 (받아오기)

    - ##### _getWork(work_id)_

  - 작품 cost 부여 (좋아요 기능)

    - ##### _giveCostToWork(user_id, work_id)_

  - 작품 cost 부여 취소

    - ##### _clearToWorkCost(cost_userId, cost_workId)_

  - 작품 즐겨찾기

    - ##### _scrapWork(scrap_userId, scrap_workId)_

  - 작품 즐겨찾기 취소

    - ##### _clearToWorkScrap(scrap_userId, scrap_workId)_

  - 즐겨찾기 작품 목록

    - ##### _getAllScrapWork(scrap_userId)_

  - 내 작품 목록

    - ##### _getMyWorks(work_artistId)_
    
  - 내 전체 작품 수
  
    - ##### _getMyWorksCount(work_artistId)_



- ### 전시관 관리 [GalleryController]

  - 메인 갤러리에 "전시관" 추가 (Admin)

    - ##### _addGalleryToMainGallery(maingallery_id, maingallery_artistId)_

      - Main Gallery DB

  - 메인 갤러리 전시 전시관 목록 조회

    - ##### _getAllMainGallery()_

      - Main Gallery DB

  - 메인 갤러리 전시관 삭제 (Admin)

    - ##### _deleteGalleryToMainGallery(maingallery_id, maingallery_artistId)__

      - Main Gallery DB

  - 서브 갤러리에 "작품" 추가 (Admin)

    - ##### _addWorkToSubGallery(subGallery_artistId, subGallery_workId)_

  - 서브 갤러리 전시 목록 조회 

    - ##### _getAllSubGallery()_ 

  - 서브 갤러리 전시 작품 삭제 (Admin)

    - ##### _deleteWorkToSubGallery(subGallery_artistId, subGallery_workId)_

  - 개인 전시관 추가

    - ##### _addArtistGallery(gallery_id, gallery_name, gallery_desc, gallery_artistId, gallery_mainWorkId, [List]nput_workId)_

      - front단에서 전시관에 추가할 15개 이하 작품들을 선정한 후, 리스트로 묶어서 함수 매개변수로 호출

  - 개인 전시관 수정

    - ##### _addArtistGallery(gallery_id, gallery_name, gallery_desc, gallery_mainWorkId, [List]input_workId)_

  - 개인 전시관 삭제

    - ##### _deleteArtistGallery(gallery_id)_

  - 전시관 받아오기 (전시관 속 작품들 목록/상세정보)

    - ##### _getArtistGallery(galleryDetail_galleryId)_

  - 내 전시관 목록 받아오기

    - ##### _getMyGallery(gallery_artistId)_

  - 전시관 발자국 추가

    - ##### _giveFootPrintToGallery(footprint_galleryId, footprint_userId)_



- ### 관리자 관리  [AdminController]

  - 전체 사용자 조회

    - ##### _getAllUser()_

  - 작품 연령 등급 부여

    - ##### _giveRating(work_id)_

  - 작품 연령 등급 해제

    - ##### _clearRating(work_id)_

  - 추천 키워드(해시태그) 추가

    - ##### _addMainGalleryHashTag([List]input_string)_

      - keyword -> 

  - 추천 키워드(해시태그) 삭제

    - ##### _deleteMainGalleryHashTag()_

  - 추천 키워드(해시태그) 목록 조회

    - ##### _getMainGalleryHashTag()_

  - 전체 해시태그 목록 조회

    - ##### _getAllHashTag()_

  - 해시태그 삭제 

    - ##### _deleteHashTag(work_id, [input]input_string)_

      - 작품 해시태그만 삭제

    - ##### _deleteHashTag([input]input_string)_

      - 전체 작품들에서 해당 해시태그 모두 삭제

  - 메인관 작품 선정 (전시관)

    - ##### _renewMainGallery([List]input_string)_

      - 작품 추천 알고리즘

  - 서브관 작품 선정

    - ##### _renewSubGallery()_

      - -> 신규 유저 대상 작품 추천 알고리즘



- ### 메세지 관리 [MessageController]

  - 메세지 보내기

    - ##### _sendMessage(message_senderId, message_receiverId, message_title, message_content)_

      - message DB message_isCheck 컬럼 default 값 1(안읽음)

  - 메세지 목록

    - ##### _getAllMyMessage()_

  - 메세지 읽음 여부

    - ##### _checkMessage(message_id)_

      - message DB message_isCheck 컬럼 1 -> 0 (읽음)

  - 메세지 받아오기

    - ##### _getMyMessage(message_id)_

  - 메세지 삭제

    - ##### _deleteMessage(message_id)_
    
    
  
- ### 팔로우 관리 [FollowController]

  - 팔로우 하기

    - ##### _follow(follow_artistId, follow_userId)_

  - 팔로우 취소

    - ##### _cancelFollow(follow_artistId, follow_userId)_

  - 팔로잉 목록조회

    - ##### _getAllMyFollowing(follow_userId)_

  - 팔로워 목록조회

    - ##### _getAllMyFollower(follow_artistId)_

---







