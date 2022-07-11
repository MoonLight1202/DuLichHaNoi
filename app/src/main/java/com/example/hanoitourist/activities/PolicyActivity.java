package com.example.hanoitourist.activities;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.widget.NestedScrollView;

import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.ScrollView;
import android.widget.TextView;

import com.example.hanoitourist.R;

public class PolicyActivity extends AppCompatActivity {
    TextView txtChinhsach;
    NestedScrollView scrollView;
    ImageView btnBack;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate( savedInstanceState );
        setContentView( R.layout.activity_policy );
        txtChinhsach = findViewById( R.id.txtChinhsach );
        scrollView = findViewById( R.id.scroolview );
        btnBack = findViewById( R.id.btn_BackPolicy );
        String chinhsach = "Bảo vệ thông tin cá nhân khách hàng\n" +
                "\n" +
                "Ứng dụng Du lịch Hà Nôi đã công bố Quyền riêng tư của Người dùng như sau:\n" +
                "\n" +
                "1. Thu thập Thông tin Cá nhân\n" +
                "“Thông tin Cá nhân” là thông tin về bạn mang tính nhận dạng, bao gồm nhưng không giới hạn tên, số chứng minh thư nhân dân, số giấy khai sinh, số hộ chiếu, quốc tịch, địa chỉ, số điện thoại, số fax, thông tin ngân hàng, thông tin thẻ tín dụng, dân tộc, giới tính, ngày sinh, tình trạng hôn nhân, tình trạng cư trú, nền tảng giáo dục, tình trạng tài chính, sở thích cá nhân, địa chỉ thư điện tử (email) của bạn, nghề nghiệp, định danh của bạn trong Công ty Cổ phần DuLichThaiHa Việt Nam, thông tin của bạn trong Công ty Cổ phần DuLichThaiHa Việt Nam, ngành làm việc của bạn, bất kỳ thông tin nào về bạn mà bạn đã cung cấp cho Công ty Cổ phần DuLichThaiHa Việt Nam trong các đơn đăng ký, đơn xin gia nhập hoặc bất kỳ đơn tương tự nào và/hoặc bất kỳ thông tin nào về bạn đã được hoặc sẽ được Công ty Cổ phần DuLichThaiHa Việt Nam thu thập, lưu trữ, sử dụng và xử lý theo thời gian và bao gồm các thông tin cá nhân nhạy cảm như các dữ liệu về sức khỏe, tôn giác hoặc tín ngưỡng tương tự khác. Đối với nhà cung cấp dịch vụ, chúng tôi có thể sẽ thu thập dữ liệu di động viễn thông (chẳng hạn như tốc độ, gia tốc và dữ liệu phanh hãm), dữ liệu thiết bị (chẳng hạn như số IMEI và tên của ứng dụng bạn đã cài đặt trên thiết bị) và dữ liệu đăng ký xe của bạn.\n" +
                "\n" +
                "2. Mục đích và phạm vi sử dụng thông tin\n" +
                "a. Phạm vi thu thập thông tin\n" +
                "Việc người dùng cung cấp Thông tin Cá nhân của người dùng là hoàn toàn tự nguyện. Tuy nhiên, nếu người dùng không cung cấp cho Công ty Thông tin Cá nhân của người dùng, Công ty sẽ không thể xử lý Thông tin Cá nhân của người dùng cho các Mục đích và Mục đích Bổ sung như được nêu dưới đây dẫn đến việc DuLichThaiHa không thể cung cấp các sản phẩm hoặc dịch vụ cho bạn hoặc không thể chấp nhận thanh toán do bạn thực hiện.\n" +
                "\n" +
                "Nếu người dùng là một đại lý, người bán hàng hoặc nhà cung cấp dịch vụ, việc cung cấp Thông tin Cá nhân của người dùng là bắt buộc, và việc không cung cấp Thông tin Cá nhân của người dùng sẽ là hành vi vi phạm pháp luật hoặc các quy định pháp lý, và có thể khiến Công ty không thể hợp tác với người dùng để cung cấp các dịch vụ hoặc sản phẩm hoặc thực hiện các khoản thanh toán cho người dùng về sản phẩm hoặc dịch vụ mà người dùng cung cấp.\n" +
                "\n" +
                "Bên cạnh những Thông tin Cá nhân người dùng trực tiếp cung cấp cho Công ty, Công ty có thể thu thập Thông tin Cá nhân của người dùng từ nhiều nguồn khác nhau như:\n" +
                "\n" +
                "Các mẫu đơn đăng ký hoặc đề nghị sử dụng, công cụ khảo sát và đào tạo, hoặc các tài liệu tương tự khác;\n" +
                "Các nguồn thông tin đại chúng khác như danh bạ;\n" +
                "Các trang mạng xã hội của DuLichThaiHa mà bạn theo dõi, yêu thích hoặc là người hâm mộ của các trang đó;\n" +
                "Các tổ chức cung cấp thông tin tín dụng, bên cung cấp bảo hiểm, hoặc tổ chức cung cấp dịch vụ tín dụng;\n" +
                "Thông qua việc bạn liên lạc, trao đổi với DuLichThaiHa tại bất kỳ sự kiện hay hoạt động nào;\n" +
                "Thông qua việc bạn tham gia các cuộc thi do DuLichThaiHa tổ chức;\n" +
                "Các tổ chức hoặc đơn vị khác nhau liên kết với DuLichThaiHa;\n" +
                "Các tổ chức cung cấp dịch vụ cho bạn hoặc có mối quan hệ hợp đồng với bạn;\n" +
                "Các nhà cung cấp dịch vụ tiếp thị (marketing) hoặc đối tác tiếp thị (marketing);\n" +
                "hoặc\n" +
                "\n" +
                "Thông qua việc sử dụng các ứng dụng, trang web của DuLichThaiHa, bao gồm tất cả các ứng dụng, trang web do DuLichThaiHa điều hành và mang nhãn hiệu tương ứng (các “Ứng Dụng” và “ Trang Web”);\n" +
                "Thông Tin Cá Nhân của bạn cũng có thể được thu thập từ các cookies được sử dụng trên các Trang Web và trong trường hợp bạn là một nhà cung cấp dịch vụ, Thông Tin Cá Nhân của bạn còn có thể được thu thập từ dữ liệu hộp đen, dữ liệu GPS, dữ liệu thiết bị và khi ứng dụng DuLichThaiHa Driver của bạn đang hoạt động.\n" +
                "\n" +
                "Ngoài ra, một số nhà cung cấp dịch vụ của DuLichThaiHa còn có thể sử dụng máy ghi hình trên xe (in-vehicle camera) vì mục đích an toàn và an ninh. Mặc dù việc sử dụng các máy ghi hình trên xe này không được ủng hộ hoặc cấm cụ thể bởi DuLichThaiHa theo đây lưu ý rằng chúng tôi không thu thập, sử dụng hoặc tiết lộ bất kỳ Thông Tin Cá Nhân nào mà không được thực hiện nhân danh DuLichThaiHa. Việc thu thập, sử dụng và tiết lộ Thông Tin Cá Nhân có được từ các máy ghi hình cá nhân ở trên xe là trách nhiệm duy nhất của bên cung cấp dịch vụ và bạn có thể thông báo cho bên cung cấp dịch vụ ngừng việc này trong trường hợp bạn không đồng ý sử dụng máy ghi hình trên xe.\n" +
                "\n" +
                "b. Mục đích và phạm vi sử dụng thông tin\n" +
                "Công ty có thể sử dụng và xử lý Thông Tin Cá Nhân của bạn vì mục đích kinh doanh, vận chuyển, giao hàng và giao nhận hàng hóa (logistics) (bao gồm giao nhận thức ăn và đồ uống), hoạt động thanh toán và các hoạt động khác của Công ty mà có thể bao gồm, không giới hạn bởi các mục đích sau đây (“Mục Đích”):\n" +
                "\n" +
                "Mục đích chung:\n" +
                "\n" +
                "Để trả lời các câu hỏi, bình luận và phản hồi của người dùng;\n" +
                "Để liên lạc với người dùng về bất kỳ mục đích nào được liệt kê tại Thông báo này;\n" +
                "Để phục vụ mục đích quản lý nội bộ như kiểm toán, phân tích dữ liệu, lưu trữ cơ sở dữ liệu;\n" +
                "Để phục vụ mục đích phát hiện, ngăn chặn và truy tố tội phạm;\n" +
                "Để giúp Công ty tuân thủ các nghĩa vụ theo quy định của pháp luật;\n" +
                "Đối với khách hàng sử dụng dịch vụ do Công ty cung cấp:\n" +
                "\n" +
                "Để thực hiện các nghĩa vụ của Công ty theo bất kỳ thỏa thuận nào được ký kết với khách hàng;\n" +
                "Để cung cấp cho khách hàng bất kỳ dịch vụ nào mà khách hàng yêu cầu;\n" +
                "Để xử lý các đăng ký của khách hàng và để cung cấp các dịch vụ cho khách hàng;\n" +
                "Khi khách hàng yêu cầu tải xuống và sử dụng ứng dụng DuLichThaiHa, hoặc để xử lý yêu cầu của khách hàng, để cung cấp Ứng dụng cho khách hàng và để cung cấp cho khách hàng giấy phép sử dụng Ứng dụng;\n" +
                "Để xử lý việc tham gia của khách hàng vào bất kỳ sự kiện, hoạt động, nhóm trọng điểm, nghiên cứu, cuộc thi, chương trình khuyến mãi, cuộc bình chọn, khảo sát hoặc sản phẩm nào;\n" +
                "Để xử lý, quản lý hoặc xác minh việc đăng ký theo dõi của khách hàng đối với Công ty và để cung cấp cho khách hàng các lợi ích dành cho người theo dõi;\n" +
                "Để xác nhận các đặt hàng của khách hàng và xử lý các thanh toán liên quan đến bất kỳ sản phẩm hay dịch vụ nào mà khách hàng đã yêu cầu;\n" +
                "Để hiểu và phân tích việc kinh doanh của chúng tôi cũng như các nhu cầu và sở thích của khách hàng;\n" +
                "Để phát triển, tăng cường, và cung cấp các sản phẩm và dịch vụ để đáp ứng được nhu cầu của khách hàng;\n" +
                "Để xử lý việc trao đổi hoặc trả lại sản phẩm;\n" +
                "Đối với khách hàng là đại lý, người bán hàng, nhà cung cấp, đối tác, nhà thầu hoặc nhà cung cấp dịch vụ:\n" +
                "\n" +
                "Để cung cấp dịch vụ hoặc hàng hóa;\n" +
                "Nhằm tạo điều kiện hoặc cho phép bất kỳ sự kiểm tra nào mà DuLichThaiHa có thể yêu cầu để hợp tác với bạn;\n" +
                "Nhằm xử lý các thanh toán liên quan đến bất kỳ sản phẩm hoặc dịch vụ nào mà bạn cung cấp;\n" +
                "Nhằm cung cấp các phản hồi cá nhân đến bạn, để từ đó bạn có thể chỉ ra các khía cạnh cần được nâng cao;\n" +
                "Nhằm đánh giá mức độ an toàn và chất lượng;\n" +
                "Giám sát việc tuân thủ các điều khoản và điều kiện, chính sách và Quy tắc ứng xử của người lái xe;\n" +
                "Cung cấp thông tin cập nhật cho các đối tác vận tải của chúng tôi cho mục đích quản lý hoạt động vận tải;\n" +
                "và\n" +
                "\n" +
                "Nhằm liên hệ với bạn liên quan đến việc cung cấp dịch vụ cho bạn.;\n" +
                "Mục đích tiếp thị và quảng bá:\n" +
                "\n" +
                "Công ty cũng sử dụng và xử lý thông tin của khách hàng nhằm mục đích tiếp thị hoặc quảng bá (Mục đích bổ sung) thông qua đường bưu điện, điện thoại, tin nhắn sms, trực tiếp và/hoặc qua email, cụ thể như sau:\n" +
                "\n" +
                "Gửi cho khách hàng các cảnh báo, bản tin, cập nhật, bưu phẩm, tài liệu quảng cáo, đặc quyền, lời chúc mừng trong các dịp đặc biệt từ Công ty, các đối tác, nhà tài trợ và nhà quảng cáo của Công ty;;\n" +
                "Thông báo và gửi giấy mời cho khách hàng về các sự kiện hoặc hoạt động do Công ty, đối tác, nhà tài trợ hoặc nhà quảng cáo của Công ty;;\n" +
                "Xử lý các đăng ký tham gia của khách hàng vào các sự kiện hoặc hoạt động và để liên lạc với khách hàng về sự tham gia của khách hàng tại sự kiện hoặc hoạt động đó;;\n" +
                "Chia sẻ Thông tin Cá nhân của khách hàng giữa các công ty con, công ty liên kết và liên doanh cũng như các đại lý, người bán hàng, nhà cung cấp dịch vụ, đối tác, nhà thầu, những bên có thể liên lạc với khách hàng để tiếp thị về sản phẩm, dịch vụ, sự kiện hoặc khuyến mại của họ.;\n" +
                "3. Thời gian lưu trữ và xử lý thông tin\n" +
                "Dữ liệu cá nhân của người dùng sẽ được lưu trữ cho đến khi có yêu cầu hủy bỏ hoặc tự người dùng đăng nhập và thực hiện hủy bỏ. Còn lại trong mọi trường hợp thông tin cá nhân của người dùng sẽ được bảo mật trên máy chủ của DuLichThaiHa.\n" +
                "\n" +
                "Trong trường hợp bạn có nhu cầu ngừng đăng ký xử lý Thông Tin Cá Nhân cho các Mục Đích Bổ Sung được thực hiện bởi DuLichThaiHa, vui lòng bấm vào đường link “Ngừng Đăng Ký” được gắn vào email hoặc tin nhắn có liên quan để không phải nhận các tin nhắn này trong tương lai. Vui lòng ghi chú rằng trong trường hợp bạn đã ngừng đăng ký, chúng tôi vẫn có thể tiếp tục gửi cho bạn các tin nhắn có liên quan đến dịch vụ chẳng hạn như biên nhận đối với chuyến đi.\n" +
                "\n" +
                "Trong trường hợp bạn muốn hủy chấp thuận cho phép DuLichThaiHa thu thập thông tin phục vụ Mục Đích được quy định trong chính sách này, vui lòng thông báo cho DuLichThaiHa theo thông tin liên hệ được nêu dưới đây.\n" +
                "\n" +
                "4. Chuyển giao Thông tin Cá nhân\n" +
                "Thông tin Cá nhân của người dùng có thể được chuyển giao tới, lưu trữ, sử dụng và xử lý cho các công ty thuộc Công ty nằm ngoài quốc gia của người dùng hoặc Quốc gia Thay thế và/hoặc khi các máy chủ của Công ty được đặt ngoài quốc gia của người dùng hoặc Quốc gia Thay thế. Người dùng hiểu và đồng ý rằng việc chuyển giao Thông tin của người dùng ra khỏi quốc gia của người dùng hoặc Quốc gia Thay thế theo Chính sách này.\n" +
                "\n" +
                "5. Tiết lộ cho Bên thứ ba\n" +
                "Thông tin Cá nhân của người dùng có thể được chuyển giao, truy cập hoặc tiết lộ cho bên thứ ba để phục vụ các Mục đích và Mục đích Bổ sung. Ngoài ra, Công ty có thể làm việc với các công ty, nhà cung cấp dịch vụ hoặc cá nhân khác để thay mặt Công ty thực hiện các chức năng, và vì vậy có thể cung cấp quyền tiếp cận hoặc tiết lộ Thông tin Cá nhân của người dùng cho nhà cung cấp dịch vụ hoặc bên thứ ba đó. Bên thứ ba bao gồm, nhưng không giới hạn:\n" +
                "\n" +
                "Các đối tác của Công ty, bao gồm các bên Công ty cộng tác trong các sự kiện, chương trình và hoạt động nhất định;;\n" +
                "Các công ty tổ chức sự kiện và nhà tài trợ sự kiện;;\n" +
                "Các công ty nghiên cứu thị trường;;\n" +
                "Các nhà cung cấp dịch vụ, bao gồm, các nhà cung cấp dịch vụ công nghệ thông tin (CNTT) về cơ sở hạ tầng, phần mềm và công tác phát triển;;\n" +
                "Các cố vấn chuyên môn và kiểm toán viên bên ngoài, bao gồm cố vấn pháp lý, cố vấn tài chính và chuyên gia tư vấn;;\n" +
                "Các tổ chức khác trong Công ty;;\n" +
                "và\n" +
                "\n" +
                "Các cơ quan Chính phủ để thực hiện các quy định của pháp luật;;\n" +
                "Thông tin Cá nhân cũng có thể được chia sẻ liên quan đến giao dịch doanh nghiệp, ví dụ như bán chi nhánh hoặc bộ phận, sáp nhập, hợp nhất, hoặc bán tài sản, hoặc trong trường hợp hiếm là giải thể doanh nghiệp.\n" +
                "\n" +
                "6. Phương thức, lưu trữ và công cụ để người tiêu dùng tiếp cận và chỉnh sửa dữ liệu cá nhân của mình\n" +
                "Tùy thuộc vào bất kỳ ngoại lệ nào theo pháp luật hiện hành của quốc gia của người dùng hoặc Quốc gia Thay thế, người dùng có thể yêu cầu quyền truy cập và/hoặc yêu cầu đính chính Thông tin Cá nhân, yêu cầu hạn chế việc xử lý Thông tin Cá nhân của người dùng cho các Mục đích Bổ sung và/hoặc đưa ra bất kỳ thắc mắc nào liên quan đến Thông tin Cá nhân của người dùng bằng cách liên hệ tới:\n" +
                "\n" +
                "Công ty Cổ phần DuLichThaiHa Việt Nam\n" +
                "\n" +
                "Địa chỉ: Tầng 12A, toà nhà VTC Online, 18 Tam Trinh, Hai Bà Trưng, Hà Nội.\n" +
                "\n" +
                "Tuân thủ pháp luật tại quốc gia của người dùng hoặc Quốc gia Thay thế, Công ty có quyền lưu trữ và áp dụng một mức phí cho việc truy cập Thông tin Cá nhân của người dùng trong mức cho phép.\n" +
                "\n" +
                "Đối với quyền truy cập và/hoặc đính chính Thông tin Cá nhân của người dùng, Công ty có quyền từ chối các yêu cầu truy cập và/hoặc đính chính Thông tin Cá nhân của người dùng với các lý do cho phép theo pháp luật, ví dụ như khi chi phí để cho người dùng quyền truy cập không tương xứng với những rủi ro đối với quyền riêng tư của người dùng hoặc của một người khác.\n" +
                "\n" +
                "Cookies\n" +
                "DuLichThaiHa và các bên DuLichThaiHa hợp tác, có thể sẽ sử dụng cookies, web beacon, web tag, 3web script, các dữ liệu chia sẻ như HTML5 và Flash (còn có thể được gọi là “flash cookies”), công cụ định danh quảng cáo (bao gồm công cụ định danh di động như Apple’s IDFA hoặc Google Advertising ID) và các công nghệ tương tự (theo đây được gọi chung là “Cookies”) có liên quan đến việc sử dụng các Trang Web và Ứng Dụng của bạn. Cookies có thể chứa đựng các công cụ định danh độc nhất và trú tại, ngoài các nơi khác, máy tính hoặc thiết bị di động của bạn, trong email mà chúng tôi gửi cho bạn, và trên các trang web của chúng tôi. Cookies có thể truyền tải thông tin về bạn và việc sử dụng Dịch Vụ của bạn, chẳng hạn như loại trình duyệt web của bạn, tìm kiếm yêu thích, địa chỉ IP, dữ liệu có liên quan đến quảng cáo mà được trình chiếu cho bạn hoặc được bạn kích vào, và ngày giờ sử dụng của bạn. Cookies có thể được lưu trữ trong từng phần cụ thể.\n" +
                "\n" +
                "DuLichThaiHa có thể cho phép các bên thứ ba sử dụng Cookies trên các Trang Web và Ứng Dụng để thu thập các thông tin cùng loại vì cùng các mục đích mà DuLichThaiHa tự thực hiện. Các bên thứ ba sẽ có thể liên kết các thông tin mà họ thu thập được với các thông tin khác mà họ đã có về bạn từ các nguồn thông tin khác. Chúng tôi không nhất thiết phải truy cập hoặc kiểm soát các Cookies mà họ sử dụng.\n" +
                "\n" +
                "Ngoài ra, chúng tôi sẽ có thể chia sẻ thông tin không cá nhân từ hoặc về bạn với cá bên thứ ba, chẳng hạn như dữ liệu định vị, chỉ dẫn quảng cáo hoặc một dữ liệu giải mã từ một chỉ dẫn tài khoản (chẳng hạn như địa chỉ email), để phục vụ cho việc thể hiện các quảng cáo mục tiêu.\n" +
                "\n" +
                "Trong trường hợp bạn không muốn Thông Tin Cá Nhân của bạn được thu thập qua cookies trên Trang Web, bạn có thể tắt cookies bằng cách điều chỉnh cài đặt trình duyệt internet của bạn để vô hiệu hóa, khóa hoặc tắt cookies, bằng cách xóa lịch sử truy cập của bạn hoặc xóa bộ nhớ cache khỏi trình duyệt internet của bạn. Bạn cũng có thể hạn chế việc chia sẻ một số thông tin này trong phần cài đặt trên thiết bị di động của bạn, hoặc bằng việc cung cấp thông tin.\n" +
                "\n" +
                "7. Các quy định khác\n" +
                "Liên kết với Trang Web của Bên thứ ba\n" +
                "Các Trang Web có thể chứa các liên kết tới các trang web của các bên thứ ba. Xin vui lòng lưu ý rằng Công ty không chịu trách nhiệm đối với việc thu thập, sử dụng, duy trì, chia sẻ, hoặc tiết lộ dữ liệu và thông tin của các bên thứ ba đó. Nếu người dùng trực tiếp cung cấp thông tin cho những trang đó, chính sách bảo mật và điều khoản sử dụng của những trang đó sẽ được áp dụng và Công ty không chịu trách nhiệm đối với các hoạt động xử lý thông tin hoặc các chính sách bảo mật của những trang đó.\n" +
                "\n" +
                "Thông tin Cá nhân của Trẻ chưa thành niên và Cá nhân Khác\n" +
                "Nếu người dùng là một bậc cha mẹ hoặc người giám hộ, xin vui lòng không cho phép trẻ chưa thành niên (người dưới 18 (mười tám) tuổi) dưới sự giám hộ của người dùng gửi Thông tin Cá nhân tới Công ty. Trong trường hợp Thông tin Cá nhân như vậy được cung cấp cho Công ty, bằng cách này người dùng chấp thuận cho việc xử lý Thông tin Cá nhân của trẻ chưa thành niên và cá nhân người dùng chấp nhận và đồng ý ràng buộc bởi Thông báo này và chịu trách nhiệm đối với các hành động của trẻ.\n" +
                "\n" +
                "Trong một số trường hợp người dùng có thể đã cung cấp Thông tin Cá nhân liên quan đến cá nhân khác (ví dụ như vợ/chồng, các thành viên trong gia đình hoặc bạn bè của người dùng) và trong những trường hợp đó người dùng thay mặt và đảm bảo rằng người dùng được ủy quyền cung cấp Thông tin Cá nhân của họ cho Công ty và người dùng đã có sự chấp thuận của họ về việc Thông tin Cá nhân của họ được xử lý và sử dụng theo cách thức như đã nêu trong Thông báo này.\n" +
                "\n" +
                "Đồng ý và Chấp thuận\n" +
                "Bằng cách trao đổi với Công ty, sử dụng các dịch vụ của Công ty, mua các sản phẩm từ Công ty hoặc qua làm việc với Công ty, người dùng thừa nhận rằng người dùng đã đọc và hiểu Thông báo này và đồng ý và chấp thuận việc Công ty sử dụng, xử lý và chuyển giao Thông tin Cá nhân của người dùng như được mô tả trong Thông báo này.\n" +
                "\n" +
                "Công ty có quyền chỉnh sửa, cập nhật hoặc sửa đổi các điều khoản của Thông báo này bất cứ lúc nào bằng cách công bố Thông báo cập nhật trên các Trang Web. Bằng cách tiếp tục trao đổi với Công ty, bằng cách tiếp tục sử dụng các dịch vụ của Công ty, mua các sản phẩm từ Công ty hoặc qua tiếp tục làm việc với Công ty sau khi Thông báo này được chỉnh sửa, cập nhật hoặc sửa đổi, những hành động như vậy có nghĩa là người dùng chấp nhận những chỉnh sửa, cập nhật hoặc sửa đổi đó.\n" +
                "\n" +
                "Trong trường hợp có mâu thuẫn giữa bản tiếng Việt và ngôn ngữ khác của Thông báo này, bản tiếng Việt được ưu tiên áp dụng.\n" +
                "\n" +
                "8. Cam kết bảo mật thông tin cá nhân khách hàng\n" +
                "Thông tin cá nhân của người dùng trên Ứng dụng của DuLichThaiHa được DuLichThaiHa cam kết bảo mật và tuân theo Chính sách này.\n" +
                "\n" +
                "Trong trường hợp máy chủ lưu trữ thông tin bị hacker tấn công dẫn đến mất mát dữ liệu cá nhân của thành viên, DuLichThaiHa sẽ có trách nhiệm thông báo vụ việc cho cơ quan chức năng điều tra xử lý kịp thời và thông báo cho người dùng được biết.\n" +
                "\n" +
                "Ban quản lý DuLichThaiHa yêu cầu các cá nhân khi đăng ký/mua hàng là Thành Viên, phải cung cấp đầy đủ thông tin cá nhân có liên quan như: họ và tên, địa chỉ liên lạc, địa chỉ thư điện tử, số chứng minh nhân dân, điện thoại, số tài khoản, số thẻ thanh toán…, và chịu trách nhiệm về tính chính xác, pháp lý và cập nhật của những thông tin trên. Ban quản lý DuLichThaiHa không chịu trách nhiệm cũng như không giải quyết mọi khiếu nại có liên quan đến quyền lợi của người dùng nếu xét thấy thông tin cá nhân của người dùng đó cung cấp là không chính xác.”\n" +
                "\n" +
                "9. Cơ chế giải quyết khiếu nại, tranh chấp\n" +
                "Khi phát sinh khiếu nại, tranh chấp liên quan tới thông tin cá nhân của người dùng, Công ty đề cao giải pháp thương lượng, hòa giải giữa các bên nhằm duy trì sự tin cậy của thành viên vào chất lượng dịch vụ của Công ty và thực hiện theo các bước sau:\n" +
                "\n" +
                "Bước 1: Người dùng khiếu nại về vụ việc liên quan tới thông tin cá nhân của mình tới DuLichThaiHa bằng cách gọi điện thoại đến số 1900 2683, gửi phản hồi từ trang mạng xã hội Facebook chính thức của DuLichThaiHa, từ ứng dụng DuLichThaiHa, và/hoặc các hình thức khác không trái quy định pháp luật\n" +
                "\n" +
                "Bước 2: Bộ phận Chăm sóc Khách hàng của DuLichThaiHa sẽ tiếp nhận các khiếu nại của người dùng, tùy theo tính chất và mức độ của khiếu nại, DuLichThaiHa sẽ có những biện pháp cụ thể để hỗ trợ người dùng giải quyết tranh chấp đó và trả lời kết quả cho người dùng trong thời hạn 10 ngày làm việc.\n" +
                "\n" +
                "Trong trường hợp sự việc nằm ngoài khả năng và thẩm quyền giải quyết của DuLichThaiHa, DuLichThaiHa sẽ yêu cầu người dùng đưa sự việc tới cơ quan nhà nước có thẩm quyền giải quyết theo pháp luật.\n" +
                "\n" +
                "Công ty tôn trọng và nghiêm túc thực hiện các quy định về bảo vệ quyền lợi người tiêu dùng và bảo vệ thông tin cá nhân của người dùng. Vì vậy, Công ty cũng đề nghị người dùng tôn trọng và tuân th";
        txtChinhsach.setText( chinhsach );
        btnBack.setOnClickListener( new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                finish();
            }
        } );
    }
}