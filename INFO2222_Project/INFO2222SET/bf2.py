import requests
from lxml import html
from concurrent.futures import ThreadPoolExecutor,ProcessPoolExecutor
from concurrent import futures
import threading

class shitCode(object):

    def __init__(self):
        self.a_token_url = 'https://canvas.sydney.edu.au/courses/30588/quizzes/142845/take'
        self.up_data_url = 'https://canvas.sydney.edu.au/courses/30588/quizzes/142845/submissions?user_id=189746'
        self.now_queue = 1180

    def header(self):
        headers = {
            'authority': 'canvas.sydney.edu.au',
            'cache-control': 'max-age=0',
            'sec-ch-ua': '" Not;A Brand";v="99", "Google Chrome";v="91", "Chromium";v="91"',
            'sec-ch-ua-mobile': '?0',
            'upgrade-insecure-requests': '1',
            'origin': 'https://canvas.sydney.edu.au',
            'content-type': 'application/x-www-form-urlencoded',
            'user-agent': 'Mozilla/5.0 (Macintosh; Intel Mac OS X 10_15_3) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/91.0.4472.19 Safari/537.36',
            'accept': 'text/html,application/xhtml+xml,application/xml;q=0.9,image/avif,image/webp,image/apng,*/*;q=0.8,application/signed-exchange;v=b3;q=0.9',
            'sec-fetch-site': 'same-origin',
            'sec-fetch-mode': 'navigate',
            'sec-fetch-dest': 'document',
            'referer': 'https://canvas.sydney.edu.au/courses/30588/quizzes/142845',
            'accept-language': 'zh-CN,zh;q=0.9,en;q=0.8',
            'cookie': 'log_session_id=1c7be4cdcdb36628bdbcea69e6efe88f; _legacy_normandy_session=iMWq1ecRizAx1wlAq8VNsw+7tv6DyidrlvYf5sXNSYjkW61KBkHLiuEtTIHqOZhAqvH7a1IXQj_QQCqRYwmIkPUL49M0yQ2v6Aic-hbDW2o55syWWOWVLGVBpRHv7wjSSvyUepwTPLJx_CImBd1D507SqOikhJ7s3VZH0s6v-k1e8v_xqPkECtVEk5yO_kH3L-YkdVW6f3rYPmiZNDXOm9DH2hrMmwiCtTv72GcIqy-F0ffKo6uJ37KrOlA-roquA_55mEW6P3_kpwcfGJuSDy2EEz2ob4mKQFJ5hCcaJGP01x1W3dt_ZZW-NDSgARMBCXGXICUB8ameVQi_ENU41ryRs4I4b-fRCmji1Q0_iKX-sXyDAppzEygVR01ZnS4BJhqMNdVErIRwfQXsIPmByghMEmblVh2KCRf_TZwLqfxiKfahi-5p0CGcje_w-hG6sAMLDtz7qSH4VG4-Ia5fsPz-UXNQ-QX0xqXNI50Yt_qMTMSDh_nWI-O5R7lTicpD2RFvhGzKYD94ZIiGRIqwiGN9OPl_fZoiHFmoFDzESeCRTpxY-OvtTg-iNSWBRBNijsR4UMMIhCXwCBzOv51fnhT.e635yNFE2gEe81cMXBeWLitKGNo.YJObdg; canvas_session=iMWq1ecRizAx1wlAq8VNsw+7tv6DyidrlvYf5sXNSYjkW61KBkHLiuEtTIHqOZhAqvH7a1IXQj_QQCqRYwmIkPUL49M0yQ2v6Aic-hbDW2o55syWWOWVLGVBpRHv7wjSSvyUepwTPLJx_CImBd1D507SqOikhJ7s3VZH0s6v-k1e8v_xqPkECtVEk5yO_kH3L-YkdVW6f3rYPmiZNDXOm9DH2hrMmwiCtTv72GcIqy-F0ffKo6uJ37KrOlA-roquA_55mEW6P3_kpwcfGJuSDy2EEz2ob4mKQFJ5hCcaJGP01x1W3dt_ZZW-NDSgARMBCXGXICUB8ameVQi_ENU41ryRs4I4b-fRCmji1Q0_iKX-sXyDAppzEygVR01ZnS4BJhqMNdVErIRwfQXsIPmByghMEmblVh2KCRf_TZwLqfxiKfahi-5p0CGcje_w-hG6sAMLDtz7qSH4VG4-Ia5fsPz-UXNQ-QX0xqXNI50Yt_qMTMSDh_nWI-O5R7lTicpD2RFvhGzKYD94ZIiGRIqwiGN9OPl_fZoiHFmoFDzESeCRTpxY-OvtTg-iNSWBRBNijsR4UMMIhCXwCBzOv51fnhT.e635yNFE2gEe81cMXBeWLitKGNo.YJObdg; _csrf_token=F5%2BmGxhVZR%2F8c8%2FfaKD%2BMU3m%2FkQZ9Ea7M5SrW%2FIPvolvz%2FRVWxwSR4tGmfQdk8pzOaLJcHWyM%2Bt%2F2voetFzs5g%3D%3D',
        }
        return headers

    def get_a_token(self):
        params = (
            ('user_id', '189746'),
        )

        data = {
            '_method': 'post',
            'authenticity_token': 'F5+mGxhVZR/8c8/faKD+MU3m/kQZ9Ea7M5SrW/IPvolvz/RVWxwSR4tGmfQdk8pzOaLJcHWyM+t/2voetFzs5g=='
        }

        response = requests.post('https://canvas.sydney.edu.au/courses/30588/quizzes/142845/take', headers=self.header(),
                                 params=params, data=data)
        print(response.status_code)
        etree = html.etree
        htm = etree.HTML(response.text)
        a_token = htm.xpath('.//input[@name="authenticity_token"]/@value')[0]
        return a_token

    def do_action(self):
        for i in range(0, 7400):
            try:
                a_token = self.get_a_token()
                if len(a_token):
                    params = (
                        ('user_id', '189746'),
                    )
                    data = {
                        'utf8': '\u2713',
                        'authenticity_token': a_token,
                        'attempt': self.now_queue,
                        'validation_token': 'd8e3e6ef4cefbb2b34f276b34be5f120369658e4126f3ecdd77ea7b61c085357',
                        'question_1475128': str(7400 - i)
                    }
                    response = requests.post(
                        'https://canvas.sydney.edu.au/courses/30588/quizzes/142845/submissions?user_id=189746',
                        params=params, data=data, headers=self.header())
                    code = response.status_code

                    print('status_code: ',code,'----num: ',str(7400 - i), 'attempt: ', self.now_queue)
                    self.now_queue += 1
            except Exception as e:
                print(e)

    # def run(self):
    #     thread_pool = ThreadPoolExecutor(20)
    #     for i in range(1,8864):
    #         thread_pool.submit(self.do_action, i)

if __name__ == '__main__':
    c = shitCode()
    c.do_action()