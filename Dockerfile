FROM openjdk:8
RUN mkdir app
WORKDIR app
ENV TZ America/Bogota
RUN ln -snf /usr/share/zoneinfo/$TZ /etc/localtime && \
    echo $TZ > /etc/timezone && \
    dpkg-reconfigure --frontend noninteractive tzdata
COPY ./target/universal/beverly-citas-1.0-SNAPSHOT.zip /app
RUN unzip beverly-citas-1.0-SNAPSHOT.zip -d beverly-citas
EXPOSE 9001
ENTRYPOINT ["beverly-citas/beverly-citas-1.0-SNAPSHOT/bin/beverly-citas", "-Daws.access_key_id=AKIA2LHT56OXDESCZC57", "-Daws.secret_access_key=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", "-Daws.topicArn=arn:aws:sns:us-west-1:711328658350:citas", "-Dhttp.port=9001"]