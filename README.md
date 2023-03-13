# 아티스트 굿즈 자유거래 플랫폼
<aside>
🌲 master

- dev
    - dev-front
        - feature/기능명
        - feature/mainpage
    - dev-back
        - feature/기능명
    - fix : 문제가 생긴 브랜치에서 분기
        - fix-front/기능명
        - fix-back/기능명
- docs/문서타입[ex) README, ppt]
</aside>

브랜치 전략
- 젠킨스 배포를 위해 백, 프론트로 크게 브랜치를 나눔(dev-).
- dev-front, dev-back에서 각각 브랜치를 파서 작업 후, 완성한 기능은 머지.
- 문제가 생긴 브랜치에서 분기(fix-)해서 수정 후 정상화하면 머지(merge).


