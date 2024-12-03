import "./SuccessStyles.scss";
import { CircleCheck } from 'lucide-react';

const Success = () => {
    return (
        <div className="container">
            <div className="row justify-content-center">
                <div className="col-md-5">
                    <div className="message-box _success">
                        <CircleCheck color="#28a745" size="48px"/>
                        <h2> Your payment was successful </h2>
                        <p> Thank you for your payment. we will <br/>
                        be in contact with more details shortly </p>   
                    </div>
                </div>
            </div>
        </div>
    );
}

export default Success;