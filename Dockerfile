FROM postgres:latest
ENV POSTGRES_USER postgres
ENV POSTGRES_PASSWORD password
ENV POSTGRES_DB districtDesignerBase
EXPOSE 5432
CMD ["postgres"]
